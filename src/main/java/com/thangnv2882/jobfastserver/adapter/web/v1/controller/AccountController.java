package com.thangnv2882.jobfastserver.adapter.web.v1.controller;

import com.thangnv2882.jobfastserver.adapter.web.base.RestApiV1;
import com.thangnv2882.jobfastserver.adapter.web.base.VsResponseUtil;
import com.thangnv2882.jobfastserver.application.constants.UrlConstant;
import com.thangnv2882.jobfastserver.application.input.account.ChangeAvatarInput;
import com.thangnv2882.jobfastserver.application.input.account.GetAccountByEmailInput;
import com.thangnv2882.jobfastserver.application.input.account.UpdateAccountInput;
import com.thangnv2882.jobfastserver.application.input.commons.FindAccountInput;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.output.account.GetAccountOutput;
import com.thangnv2882.jobfastserver.application.output.account.GetListAccountOutput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.output.common.UploadFileOutput;
import com.thangnv2882.jobfastserver.application.service.IAccountService;
import com.thangnv2882.jobfastserver.application.utils.UrlUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestApiV1
public class AccountController {

  private final IAccountService accountService;

  public AccountController(IAccountService accountService) {
    this.accountService = accountService;
  }

  @Operation(summary = "API Get List Account")
  @GetMapping(UrlConstant.Account.GET_ALL)
  public ResponseEntity<?> getAllAccount(@RequestBody(required = false) FindAccountInput findAccountInput) {
    // Get output
    GetListAccountOutput output = accountService.findAllAccount(findAccountInput);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @Operation(summary = "API Get Account By Id")
  @GetMapping(UrlConstant.Account.GET_BY_ID)
  public ResponseEntity<?> getAccountById(@PathVariable("idAccount") Long idAccount) {
    // Create input
    Input input = new Input(idAccount);
    // Get output
    GetAccountOutput output = accountService.findAccountById(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @Operation(summary = "API Get Account By Email")
  @GetMapping(UrlConstant.Account.GET_BY_EMAIL)
  public ResponseEntity<?> getAccountByEmail(@RequestParam("email") String email) {
    // Create input
    GetAccountByEmailInput input = new GetAccountByEmailInput(email);
    // Get output
    GetAccountOutput output = accountService.findAccountByEmail(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @Operation(summary = "API Update Account")
  @PatchMapping(UrlConstant.Account.UPDATE)
  public ResponseEntity<?> updateAccount(@Valid @RequestBody UpdateAccountInput input) {
    // Get output
    Output output = accountService.updateAccount(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @Operation(summary = "API Delete Account")
  @DeleteMapping(UrlConstant.Account.DELETE)
  public ResponseEntity<?> deleteAccount(@PathVariable("idAccount") Long idAccount) {
    // Create input
    Input input = new Input(idAccount);
    // Get output
    Output output = accountService.deleteAccount(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @Operation(summary = "API Change Avatar")
  @PatchMapping(UrlConstant.Account.CHANGE_AVATAR)
  public ResponseEntity<?> changeAvatar(@RequestParam("file") MultipartFile file,
                                        final HttpServletRequest request) {
    // Create input
    ChangeAvatarInput input = new ChangeAvatarInput(file, UrlUtil.applicationUrl(request));
    // Get output
    UploadFileOutput output;
    try {
      output = accountService.changeAvatar(input);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // Return output
    return VsResponseUtil.ok(output);
  }

}
