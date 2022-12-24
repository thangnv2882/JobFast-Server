package com.thangnv2882.jobfastserver.application.event;

import com.thangnv2882.jobfastserver.domain.entity.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
  private Account account;
  private String applicationUrl;

  public RegistrationCompleteEvent(Account account, String applicationUrl) {
    super(account);
    this.account = account;
    this.applicationUrl = applicationUrl;
  }
}
