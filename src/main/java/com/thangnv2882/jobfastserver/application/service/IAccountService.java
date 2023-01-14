package com.thangnv2882.jobfastserver.application.service;

import com.thangnv2882.jobfastserver.application.input.account.*;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.output.account.GetAccountOutput;
import com.thangnv2882.jobfastserver.application.output.account.GetListAccountOutput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.output.common.UploadFileOutput;
import com.thangnv2882.jobfastserver.domain.entity.Account;

import java.io.IOException;
import java.util.List;

public interface IAccountService {

  GetListAccountOutput findAllAccount(FindAccountInput findAccountInput);

  GetAccountOutput findAccountById(Input input);

  GetAccountOutput findAccountByEmail(GetAccountByEmailInput getAccountByEmailInput);

  Output addRoleToAccount(RoleWithAccountInput input);

  Output removeRoleToAccount(RoleWithAccountInput input);

  Output updateAccount(UpdateAccountInput input);

  Output deleteAccount(Input input);

  UploadFileOutput changeAvatar(ChangeAvatarInput input) throws IOException;

  List<Account> findAllAccountByBirthday(String birthday);

}
