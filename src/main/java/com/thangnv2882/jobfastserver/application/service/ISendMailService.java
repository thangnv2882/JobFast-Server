package com.thangnv2882.jobfastserver.application.service;

import com.thangnv2882.jobfastserver.domain.entity.Account;

import javax.mail.MessagingException;
import java.util.List;

public interface ISendMailService {
  String sendMailWithText(String title, String content, String to);
  void sendMailForBirthday(List<Account> accounts);
}
