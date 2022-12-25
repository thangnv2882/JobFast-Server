package com.thangnv2882.jobfastserver.application.service.impl;

import com.thangnv2882.jobfastserver.application.constants.*;
import com.thangnv2882.jobfastserver.application.dai.IAccountRepository;
import com.thangnv2882.jobfastserver.application.dai.IPasswordResetTokenRepository;
import com.thangnv2882.jobfastserver.application.dai.IRoleRepository;
import com.thangnv2882.jobfastserver.application.dai.IVerificationTokenRepository;
import com.thangnv2882.jobfastserver.application.event.RegistrationCompleteEvent;
import com.thangnv2882.jobfastserver.application.event.ResetPasswordCompleteEvent;
import com.thangnv2882.jobfastserver.application.input.auth.AuthenticationRequest;
import com.thangnv2882.jobfastserver.application.input.auth.UpdatePasswordInput;
import com.thangnv2882.jobfastserver.application.input.auth.SignUpInput;
import com.thangnv2882.jobfastserver.application.output.AuthenticationOutput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.service.IAuthService;
import com.thangnv2882.jobfastserver.application.utils.JwtTokenUtil;
import com.thangnv2882.jobfastserver.application.utils.UrlUtil;
import com.thangnv2882.jobfastserver.config.exception.NotFoundException;
import com.thangnv2882.jobfastserver.config.exception.VsException;
import com.thangnv2882.jobfastserver.domain.entity.Account;
import com.thangnv2882.jobfastserver.domain.entity.PasswordResetToken;
import com.thangnv2882.jobfastserver.domain.entity.Role;
import com.thangnv2882.jobfastserver.domain.entity.VerificationToken;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements IAuthService {

  private final IAccountRepository accountRepository;

  private final IRoleRepository roleRepository;

  private final IVerificationTokenRepository verificationTokenRepository;
  private final IPasswordResetTokenRepository passwordResetTokenRepository;

  private final ApplicationEventPublisher publisher;
  private final AuthenticationManager authenticationManager;

  private final MyUserDetailsService myUserDetailsService;

  private final PasswordEncoder passwordEncoder;
  private final JwtTokenUtil jwtTokenUtil;


  private final HttpServletRequest request;
  private final ModelMapper modelMapper;


  public AuthServiceImpl(IAccountRepository accountRepository, IRoleRepository roleRepository,
                         IVerificationTokenRepository verificationTokenRepository,
                         IPasswordResetTokenRepository passwordResetTokenRepository,
                         ApplicationEventPublisher publisher, AuthenticationManager authenticationManager,
                         MyUserDetailsService myUserDetailsService,
                         PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil, HttpServletRequest request,
                         ModelMapper modelMapper) {
    this.accountRepository = accountRepository;
    this.roleRepository = roleRepository;
    this.verificationTokenRepository = verificationTokenRepository;
    this.passwordResetTokenRepository = passwordResetTokenRepository;
    this.publisher = publisher;
    this.authenticationManager = authenticationManager;
    this.myUserDetailsService = myUserDetailsService;
    this.passwordEncoder = passwordEncoder;
    this.jwtTokenUtil = jwtTokenUtil;
    this.request = request;
    this.modelMapper = modelMapper;
  }

  public static void checkAccountExists(Optional<Account> account) {
    if (account.isEmpty()) {
      throw new VsException(MessageConstant.ACCOUNT_NOT_EXISTS);
    }
  }

  @Override
  public AuthenticationOutput signIn(AuthenticationRequest authenticationRequest) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              authenticationRequest.getEmail(), authenticationRequest.getPassword()
          )
      );
    } catch (BadCredentialsException e) {
      throw new NotFoundException("Incorrect email or password");
    }
    Account account = accountRepository.findByEmail(authenticationRequest.getEmail());
    if (!account.isEnable()) {
      throw new VsException(MessageConstant.ACCOUNT_NOT_EXISTS);
    }
    if (account.getDeleteFlag()) {
      throw new VsException(MessageConstant.DATA_WAS_DELETE);
    }

    UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
    String token = jwtTokenUtil.generateToken(userDetails);

    return new AuthenticationOutput(account, token);
  }

  @Override
  public Output signUp(SignUpInput input) {

    Account account = accountRepository.findByEmail(input.getEmail());
    if (account != null) {
      throw new VsException(MessageConstant.EMAIL_ALREADY_EXISTS);
    }
    account = new Account();
    modelMapper.map(input, account);

    account.setPassword(passwordEncoder.encode(input.getPassword()));
    account.setAccountType(AccountType.CANDICATE);

    if (input.getAccountType().compareTo(AccountType.COMPANY.toString()) == 0) {
      account.setAccountType(AccountType.COMPANY);
    } else if (input.getAccountType().compareTo(AccountType.CANDICATE.toString()) == 0) {
      account.setAccountType(AccountType.CANDICATE);
    } else {
      throw new NotFoundException(MessageConstant.ACCOUNT_TYPE_NOT_EXIST);
    }

    publisher.publishEvent(new RegistrationCompleteEvent(
        account,
        UrlUtil.applicationUrl(request)
    ));

    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public void saveVerificationTokenForAccount(Account account, String token) {
    VerificationToken verificationToken
        = new VerificationToken(account, token);
    verificationTokenRepository.save(verificationToken);
  }

  @Override
  public Account verificationTokenSignUp(String token) {
    VerificationToken verificationToken
        = verificationTokenRepository.findByToken(token);
    if (verificationToken == null) {
      throw new NotFoundException(MessageConstant.INVALID_TOKEN);
    }

    Account account = modelMapper.map(verificationToken.getAccount(), Account.class);

    Calendar cal = Calendar.getInstance();
    if ((verificationToken.getExpirationTime().getTime()
        - cal.getTime().getTime()) <= 0) {
      verificationTokenRepository.delete(verificationToken);
      accountRepository.delete(account);
      throw new NotFoundException(MessageConstant.EXPIRED_TOKEN);
    }

    account.setEnable(true);

    Role role = roleRepository.findByRoleName(RoleConstant.ROLE_USER);
    Set<Role> roles = new HashSet<>();
    roles.add(role);
    account.setRoles(roles);

    accountRepository.save(account);
    verificationTokenRepository.delete(verificationToken);
    return account;
  }

  @Override
  public Output createPasswordResetTokenForAccount(String email) {
    Account account = accountRepository.findByEmail(email);
    AuthServiceImpl.checkAccountExists(Optional.ofNullable(account));
    publisher.publishEvent(new ResetPasswordCompleteEvent(account, UrlUtil.applicationUrl(request)));
    return new Output(CommonConstant.TRUE, CommonConstant.SUCCESS);
  }

  @Override
  public void saveVerificationTokenResetPassword(Account account, String token) {
    PasswordResetToken passwordResetToken
        = new PasswordResetToken(account, token);
    passwordResetTokenRepository.save(passwordResetToken);
  }

  @Override
  public Output verificationTokenResetPassword(String token) {
    PasswordResetToken passwordResetToken
        = passwordResetTokenRepository.findByToken(token);
    if (passwordResetToken == null) {
      throw new NotFoundException(MessageConstant.INVALID_TOKEN);
    }
    Calendar cal = Calendar.getInstance();
    if ((passwordResetToken.getExpirationTime().getTime()
        - cal.getTime().getTime()) <= 0) {
      passwordResetTokenRepository.delete(passwordResetToken);
      throw new NotFoundException(MessageConstant.EXPIRED_TOKEN);
    }
    return new Output(CommonConstant.TRUE, AccountConstant.CONFIRMED_TOKEN_RESET_PASSWORD);
  }

  @Override
  public Output updatePassword(UpdatePasswordInput input) {
    Account account = accountRepository.findByEmail(input.getEmail());
    AuthServiceImpl.checkAccountExists(Optional.ofNullable(account));
    account.setPassword(passwordEncoder.encode(input.getNewPassword()));
    passwordResetTokenRepository.delete(passwordResetTokenRepository.findByToken(input.getToken()));
    accountRepository.save(account);
    return new Output(CommonConstant.TRUE, AccountConstant.RESET_PASSWORD_SUCCESS);
  }

}
