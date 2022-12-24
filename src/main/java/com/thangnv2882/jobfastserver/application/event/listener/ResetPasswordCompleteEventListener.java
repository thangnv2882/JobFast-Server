package com.thangnv2882.jobfastserver.application.event.listener;

import com.thangnv2882.jobfastserver.application.constants.EmailConstant;
import com.thangnv2882.jobfastserver.application.constants.UrlConstant;
import com.thangnv2882.jobfastserver.application.dai.IAccountRepository;
import com.thangnv2882.jobfastserver.application.event.ResetPasswordCompleteEvent;
import com.thangnv2882.jobfastserver.application.service.IAuthService;
import com.thangnv2882.jobfastserver.application.service.ISendMailService;
import com.thangnv2882.jobfastserver.config.exception.NotFoundException;
import com.thangnv2882.jobfastserver.domain.entity.Account;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class ResetPasswordCompleteEventListener implements
    ApplicationListener<ResetPasswordCompleteEvent> {

  private final IAuthService authService;
  private final IAccountRepository accountRepository;
  private final ISendMailService sendMailService;


  public ResetPasswordCompleteEventListener(IAuthService authService, IAccountRepository accountRepository,
                                            ISendMailService sendMailService) {
    this.authService = authService;
    this.accountRepository = accountRepository;
    this.sendMailService = sendMailService;
  }

  @SneakyThrows
  @Override
  public void onApplicationEvent(ResetPasswordCompleteEvent event) {
    // Create the Verification Password Token for the Account with link
    Account account = event.getAccount();
    String token = UUID.randomUUID().toString();

//    accountRepository.save(account);
    authService.saveVerificationTokenResetPassword(account, token);

    // Send Mail to Account
    String url =
        event.getApplicationUrl()
            + "/api/v1"
            + UrlConstant.Auth.VERIFY_RESET_PASSWORD
            + "?token=" + token;

    //sendVericationPasswordEmail
    String contentAccount =
        "We have received your request to forget your password"
            + ".\n\nTo confirm the password change, please click the following link: " + url
            + ".\n\nThank you for using our service.";

    try {
      sendMailService.sendMailWithText(EmailConstant.SUBJECT_RESET_PASSWORD, contentAccount, account.getEmail());
    } catch (Exception e) {
      String contentAdmin = "Unable to send mail to account : " + account.getEmail() + ". Please check again";
      sendMailService.sendMailWithText(EmailConstant.SUBJECT_RESET_PASSWORD, contentAdmin, EmailConstant.EMAIL_ADMIN);
      throw new NotFoundException("Send failed");
    }
  }
}
