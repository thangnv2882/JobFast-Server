package com.thangnv2882.jobfastserver.adapter.web.v1.controller;

import com.thangnv2882.jobfastserver.adapter.web.base.RestApiV1;
import com.thangnv2882.jobfastserver.adapter.web.base.VsResponseUtil;
import com.thangnv2882.jobfastserver.application.constants.UrlConstant;
import com.thangnv2882.jobfastserver.application.input.auth.AuthenticationRequest;
import com.thangnv2882.jobfastserver.application.input.auth.SignUpInput;
import com.thangnv2882.jobfastserver.application.input.auth.UpdatePasswordInput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.service.IAuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RestApiV1
public class AuthController {

  private final IAuthService authService;

  public AuthController(IAuthService authService) {
    this.authService = authService;
  }

  @Operation(summary = "API Sign In")
  @PostMapping(UrlConstant.Auth.SIGNIN)
  public ResponseEntity<?> signIn(
      @Valid @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    return VsResponseUtil.ok(authService.signIn(authenticationRequest));
  }

  @Operation(summary = "API Sign Up")
  @PostMapping(UrlConstant.Auth.SIGNUP)
  public ResponseEntity<?> signUp(@Valid @RequestBody SignUpInput input) {
    // Get output
    Output output = authService.signUp(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @Operation(summary = "API Verify Sign Up")
  @GetMapping(UrlConstant.Auth.VERIFY_SIGNUP)
  public ResponseEntity<?> verificationTokenSignUp(@RequestParam("token") String token) {
    return VsResponseUtil.ok(authService.verificationTokenSignUp(token));
  }

  @Operation(summary = "API Forgot Password")
  @PostMapping(UrlConstant.Auth.FORGOT_PASSWORD)
  public ResponseEntity<?> resetPassword(@RequestParam("email") String email) {
    return VsResponseUtil.ok(authService.createPasswordResetTokenForAccount(email));
  }

  @Operation(summary = "API Verify Reset Password")
  @GetMapping(UrlConstant.Auth.VERIFY_RESET_PASSWORD)
  public ResponseEntity<?> verificationTokenResetPassword(@RequestParam(name = "token") String token) {
    return VsResponseUtil.ok(authService.verificationTokenResetPassword(token));
  }

  @Operation(summary = "API Update Password")
  @PostMapping(UrlConstant.Auth.UPDATE_PASSWORD)
  public ResponseEntity<?> updatePassword(@Valid @RequestBody UpdatePasswordInput input) {
    return VsResponseUtil.ok(authService.updatePassword(input));
  }

}
