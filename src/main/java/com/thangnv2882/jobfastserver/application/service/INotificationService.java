package com.thangnv2882.jobfastserver.application.service;

import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.notification.CreateNotificationInput;
import com.thangnv2882.jobfastserver.application.input.notification.SendNotificationInput;
import com.thangnv2882.jobfastserver.application.input.notification.UpdateNotificationInput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.domain.entity.Notification;

import java.util.Set;

public interface INotificationService {

  Notification findNotificationById(Input input);

  Set<Notification> findNotificationByAccount(Input input);

  Output createNotification(CreateNotificationInput input);

  Output sendNotification(SendNotificationInput input);

  Output updateNotification(UpdateNotificationInput input);

  Output readNotification(Input input);

  Output readAllNotification(Input input);

  Output deleteNotification(Input input);

}
