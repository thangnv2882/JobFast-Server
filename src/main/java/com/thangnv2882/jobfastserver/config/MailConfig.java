package com.thangnv2882.jobfastserver.config;

import com.thangnv2882.jobfastserver.application.constants.EmailConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);

    mailSender.setUsername(EmailConstant.EMAIL_ADMIN);
    mailSender.setPassword(EmailConstant.MY_PASSWORD);

    Properties properties = mailSender.getJavaMailProperties();
    properties.put("mail.transport.protocol", "smtp");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.debug", "false");
        /*
            true: bật chế độ debug
            false: tắt chế độ debug
        */

    return mailSender;
  }

}
