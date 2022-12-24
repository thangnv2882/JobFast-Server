package com.thangnv2882.jobfastserver.application.service.impl;

import com.thangnv2882.jobfastserver.application.service.ISendMailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendMailServiceImpl implements ISendMailService {
  private final JavaMailSender javaMailSender;

  public SendMailServiceImpl(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  @Override
  public void sendMailWithText(String title, String content, String to) throws MessagingException {
    MimeMessage message = javaMailSender.createMimeMessage();

    MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");

    helper.setSubject(title);
    helper.setText(content);
    helper.setTo(to);

    javaMailSender.send(message);

  }
}
