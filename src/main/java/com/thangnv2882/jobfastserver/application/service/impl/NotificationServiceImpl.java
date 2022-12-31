package com.thangnv2882.jobfastserver.application.service.impl;

import com.thangnv2882.jobfastserver.application.constants.CommonConstant;
import com.thangnv2882.jobfastserver.application.constants.MessageConstant;
import com.thangnv2882.jobfastserver.application.dai.IAccountRepository;
import com.thangnv2882.jobfastserver.application.dai.INotificationRepository;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.notification.CreateNotificationInput;
import com.thangnv2882.jobfastserver.application.input.notification.UpdateNotificationInput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.service.INotificationService;
import com.thangnv2882.jobfastserver.config.exception.VsException;
import com.thangnv2882.jobfastserver.domain.entity.Account;
import com.thangnv2882.jobfastserver.domain.entity.Notification;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
  public List<Notification> findNotificationByAccount(Input input) {
    List<Notification> notifications = notificationRepository.findAllByAccount(input.getId());
    return notifications;
  }

  @Override
  public Output createNotification(CreateNotificationInput input) {
    Notification notification = modelMapper.map(input, Notification.class);
    Optional<Account> account = accountRepository.findById(input.getAccountId());
    AuthServiceImpl.checkAccountExists(account);
    notification.setAccount(account.get());

    notificationRepository.save(notification);
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
    notificationRepository.readAllNotification(input.getId());
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
