package com.thangnv2882.jobfastserver.config;

import com.thangnv2882.jobfastserver.application.service.IAccountService;
import com.thangnv2882.jobfastserver.application.service.ISendMailService;
import com.thangnv2882.jobfastserver.application.utils.DateTimeUtil;
import com.thangnv2882.jobfastserver.domain.entity.Account;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@EnableAsync
@Configuration
@EnableScheduling
public class SchedulingConfig {

  private final IAccountService accountService;
  private final ISendMailService sendMailService;

  public SchedulingConfig(IAccountService accountService, ISendMailService sendMailService) {
    this.accountService = accountService;
    this.sendMailService = sendMailService;
  }

  @Async
  @Scheduled(cron = "0 0 0 * * *")
  void sendMailAndGiveAGiftToUserForBirthday() {
    List<Account> accounts = accountService.findAllAccountByBirthday(DateTimeUtil.getDateTimeNow());
    if (accounts.size() > 0) {
      sendMailService.sendMailForBirthday(accounts);
    }
  }
}
