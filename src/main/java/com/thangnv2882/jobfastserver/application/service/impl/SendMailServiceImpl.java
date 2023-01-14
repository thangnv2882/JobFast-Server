package com.thangnv2882.jobfastserver.application.service.impl;

import com.thangnv2882.jobfastserver.application.constants.EmailConstant;
import com.thangnv2882.jobfastserver.application.service.ISendMailService;
import com.thangnv2882.jobfastserver.domain.entity.Account;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class SendMailServiceImpl implements ISendMailService {
  private final JavaMailSender javaMailSender;

  public SendMailServiceImpl(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  @Override
  public String sendMailWithText(String title, String content, String to) {
    MimeMessage message = javaMailSender.createMimeMessage();

    try {
      MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

      helper.setSubject(title);
      helper.setText(content);
      helper.setTo(to);

      javaMailSender.send(message);
    } catch (MessagingException e) {
      return EmailConstant.SEND_FAILED;
    }
    return EmailConstant.SENT_SUCCESSFULLY;
  }

  @Override
  public void sendMailForBirthday(List<Account> accounts) {
    accounts.forEach(account -> sendMailWithText(
        EmailConstant.SUBJECT_BIRTHDAY,
        "Happy birthday " + account.getName() + ". Wishing you a very happy birthday, filled with endless love and laughter. <3",
        account.getEmail()
    ));
  }

}
