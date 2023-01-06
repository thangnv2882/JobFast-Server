package com.thangnv2882.jobfastserver.application.service.impl;

import com.thangnv2882.jobfastserver.application.constants.CommonConstant;
import com.thangnv2882.jobfastserver.application.constants.MessageConstant;
import com.thangnv2882.jobfastserver.application.dai.IAccountRepository;
import com.thangnv2882.jobfastserver.application.dai.IRoleRepository;
import com.thangnv2882.jobfastserver.application.input.account.*;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.output.account.GetAccountOutput;
import com.thangnv2882.jobfastserver.application.output.account.GetListAccountOutput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.output.common.PagingMeta;
import com.thangnv2882.jobfastserver.application.output.common.UploadFileOutput;
import com.thangnv2882.jobfastserver.application.service.IAccountService;
import com.thangnv2882.jobfastserver.application.utils.FileUtil;
import com.thangnv2882.jobfastserver.application.utils.SecurityUtil;
import com.thangnv2882.jobfastserver.config.exception.VsException;
import com.thangnv2882.jobfastserver.domain.entity.Account;
import com.thangnv2882.jobfastserver.domain.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class AccountServiceImpl implements IAccountService {

  private final IAccountRepository accountRepository;
  private final IRoleRepository roleRepository;
  private final ModelMapper modelMapper;

  public AccountServiceImpl(IAccountRepository accountRepository, IRoleRepository roleRepository,
                            ModelMapper modelMapper) {
    this.accountRepository = accountRepository;
    this.roleRepository = roleRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public GetListAccountOutput findAllAccount(FindAccountInput findAccountInput) {
    Long total = accountRepository.countAccount(findAccountInput, Boolean.FALSE, Boolean.TRUE);

    PagingMeta meta = new PagingMeta(total, findAccountInput.getPageNum(), findAccountInput.getPageSize(),
        findAccountInput.getSortBy(), findAccountInput.getSortType());

    List<Account> accounts = accountRepository.findAll(findAccountInput,
        PageRequest.of(findAccountInput.getPageNum(),
            findAccountInput.getPageSize()), Sort.by(Sort.Direction.valueOf(findAccountInput.getSortType()),
            findAccountInput.getSortBy()), Boolean.FALSE, Boolean.TRUE);

    return new GetListAccountOutput(accounts, meta);
  }

  @Override
  public GetAccountOutput findAccountById(Input input) {
    Optional<Account> account = accountRepository.findById(input.getId());
    if (account.isEmpty()) {
      throw new VsException(MessageConstant.ACCOUNT_NOT_EXISTS);
    }
    if (account.get().getDeleteFlag()) {
      throw new VsException(MessageConstant.DATA_WAS_DELETE);
    }
    GetAccountOutput getAccountOutput = modelMapper.map(account.get(), GetAccountOutput.class);
    return getAccountOutput;
  }

  @Override
  public GetAccountOutput findAccountByEmail(GetAccountByEmailInput input) {
    Account account = accountRepository.findByEmail(input.getEmail());
    if (account == null) {
      throw new VsException(MessageConstant.ACCOUNT_NOT_EXISTS);
    }
    if (account.getDeleteFlag()) {
      throw new VsException(MessageConstant.DATA_WAS_DELETE);
    }
    GetAccountOutput getAccountOutput = modelMapper.map(account, GetAccountOutput.class);
    return getAccountOutput;
  }

  @Override
  public Output addRoleToAccount(RoleWithAccountInput input) {
    Optional<Account> account = accountRepository.findById(input.getId());
    AuthServiceImpl.checkAccountExists(account);
    Role role = roleRepository.findByRoleName(input.getRoleName());
    RoleServiceImpl.checkRoleExists(Optional.ofNullable(role));

    Set<Account> accounts = role.getAccounts();
    accounts.add(account.get());
    role.setAccounts(accounts);

    roleRepository.save(role);
    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public Output removeRoleToAccount(RoleWithAccountInput input) {
    Role role = roleRepository.findByRoleName(input.getRoleName());
    RoleServiceImpl.checkRoleExists(Optional.ofNullable(role));
    Set<Account> accounts = role.getAccounts();
    for (Account account : accounts) {
      if (account.getId() == input.getId()) {
        accounts.remove(account);
      }
    }
    role.setAccounts(accounts);
    roleRepository.save(role);
    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public Output updateAccount(UpdateAccountInput input) {
    Optional<Account> account = accountRepository.findById(input.getId());
    AuthServiceImpl.checkAccountExists(account);
    modelMapper.map(input, account.get());
    accountRepository.save(account.get());

    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public Output deleteAccount(Input input) {
    Optional<Account> account = accountRepository.findById(input.getId());
    AuthServiceImpl.checkAccountExists(account);
    account.get().setDeleteFlag(Boolean.TRUE);
    accountRepository.save(account.get());

    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public UploadFileOutput changeAvatar(ChangeAvatarInput input) throws IOException {
    Account account = accountRepository.findByEmail(SecurityUtil.getCurrentAccountLogin());
    AuthServiceImpl.checkAccountExists(Optional.ofNullable(account));

    UUID name = UUID.randomUUID();
    String fileName = FileUtil.saveFile(name.toString(), CommonConstant.IMAGES, input.getAvatar());
    String pathImage = input.getApplicationUrl() + CommonConstant.URL_IMAGES + fileName;

    account.setAvatar(pathImage);
    accountRepository.save(account);

    return new UploadFileOutput(CommonConstant.TRUE, CommonConstant.EMPTY_STRING, pathImage);
  }

}
