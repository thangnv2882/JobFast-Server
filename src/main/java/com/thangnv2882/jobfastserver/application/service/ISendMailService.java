package com.thangnv2882.jobfastserver.application.service;

import javax.mail.MessagingException;

public interface ISendMailService {
  void sendMailWithText(String title, String content, String to) throws MessagingException;
}
