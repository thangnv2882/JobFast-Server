package com.thangnv2882.jobfastserver.application.service;

import com.thangnv2882.jobfastserver.application.input.auth.AuthenticationRequest;
import com.thangnv2882.jobfastserver.application.input.auth.UpdatePasswordInput;
import com.thangnv2882.jobfastserver.application.input.auth.SignUpInput;
import com.thangnv2882.jobfastserver.application.output.AuthenticationOutput;
import com.thangnv2882.jobfastserver.application.output.Output;
import com.thangnv2882.jobfastserver.domain.entity.Account;

public interface IAuthService {

  AuthenticationOutput signIn(AuthenticationRequest authenticationRequest) throws Exception;

  Output signUp(SignUpInput input);

  void saveVerificationTokenForAccount(Account account, String token);

  Account verificationTokenSignUp(String token);

  Output createPasswordResetTokenForAccount(String email);

  void saveVerificationTokenResetPassword(Account account, String token);

  Output verificationTokenResetPassword(String token);

  Output updatePassword(UpdatePasswordInput input);

}
