package com.thangnv2882.jobfastserver.application.service.impl;

import com.thangnv2882.jobfastserver.application.constants.CommonConstant;
import com.thangnv2882.jobfastserver.application.constants.MessageConstant;
import com.thangnv2882.jobfastserver.application.dai.IAccountRepository;
import com.thangnv2882.jobfastserver.application.dai.INotificationRepository;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.notification.CreateNotificationInput;
import com.thangnv2882.jobfastserver.application.input.notification.SendNotificationInput;
import com.thangnv2882.jobfastserver.application.input.notification.UpdateNotificationInput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.service.INotificationService;
import com.thangnv2882.jobfastserver.config.exception.VsException;
import com.thangnv2882.jobfastserver.domain.entity.Account;
import com.thangnv2882.jobfastserver.domain.entity.Notification;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class NotificationServiceImpl implements INotificationService {

  private final INotificationRepository notificationRepository;
  private final IAccountRepository accountRepository;
  private final ModelMapper modelMapper;

  public NotificationServiceImpl(INotificationRepository notificationRepository, IAccountRepository accountRepository, ModelMapper modelMapper) {
    this.notificationRepository = notificationRepository;
    this.accountRepository = accountRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public Notification findNotificationById(Input input) {
    Optional<Notification> notification = notificationRepository.findById(input.getId());
    checkNotificationExists(notification);
    return notification.get();
  }

  @Override
  public Set<Notification> findNotificationByAccount(Input input) {
    Optional<Account> account = accountRepository.findById(input.getId());
    AuthServiceImpl.checkAccountExists(account);
    Set<Notification> notifications = account.get().getNotifications();

    return notifications;
  }

  @Override
  public Output createNotification(CreateNotificationInput input) {
    Notification notification = modelMapper.map(input, Notification.class);
    if(input.getAccountId() != null) {
      Optional<Account> account = accountRepository.findById(input.getAccountId());
      AuthServiceImpl.checkAccountExists(account);
      notification.setAccounts(Set.of(account.get()));
    }

    notificationRepository.save(notification);
    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public Output sendNotification(SendNotificationInput input) {
    Optional<Notification> notification = notificationRepository.findById(input.getNotificationId());
    checkNotificationExists(notification);
    Set<Account> accounts = notification.get().getAccounts();
    Optional<Account> account;
    for (Long i : input.getAccountId()) {
      account = accountRepository.findById(i);
      if(!account.isEmpty()) {
        accounts.add(account.get());
      }
    }
    notification.get().setAccounts(accounts);
    notificationRepository.save(notification.get());

    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public Output updateNotification(UpdateNotificationInput input) {
    Optional<Notification> notification = notificationRepository.findById(input.getId());
    checkNotificationExists(notification);

    modelMapper.map(input, notification.get());
    notificationRepository.save(notification.get());

    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public Output readNotification(Input input) {
    notificationRepository.readNotification(input.getId());
    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public Output readAllNotification(Input input) {
    Optional<Account> account = accountRepository.findById(input.getId());
    AuthServiceImpl.checkAccountExists(account);
    Set<Notification> notifications = account.get().getNotifications();
    for (Notification notification : notifications) {
      if(notification.getIsRead() == Boolean.FALSE) {
        notification.setIsRead(Boolean.TRUE);
        notificationRepository.save(notification);
      }
    }
    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  @Override
  public Output deleteNotification(Input input) {
    notificationRepository.deleteById(input.getId());
    return new Output(CommonConstant.TRUE, CommonConstant.EMPTY_STRING);
  }

  public static void checkNotificationExists(Optional<Notification> notification) {
    if (notification.isEmpty()) {
      throw new VsException(MessageConstant.NOTIFICATION_NOT_EXISTS);
    }
  }

}
