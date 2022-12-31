package com.thangnv2882.jobfastserver.application.service;

import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.commons.PageMetaInput;
import com.thangnv2882.jobfastserver.application.input.cv.UpdateCVInput;
import com.thangnv2882.jobfastserver.application.input.cv.UploadCVInput;
import com.thangnv2882.jobfastserver.application.input.notification.CreateNotificationInput;
import com.thangnv2882.jobfastserver.application.input.notification.UpdateNotificationInput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.output.common.UploadFileOutput;
import com.thangnv2882.jobfastserver.application.output.cv.GetListCVOutput;
import com.thangnv2882.jobfastserver.domain.entity.CV;
import com.thangnv2882.jobfastserver.domain.entity.Notification;

import java.io.IOException;
import java.util.List;

public interface INotificationService {

  Notification findNotificationById(Input input);

  List<Notification> findNotificationByAccount(Input input);

  Output createNotification(CreateNotificationInput input);

  Output updateNotification(UpdateNotificationInput input);

  Output readNotification(Input input);

  Output readAllNotification(Input input);

  Output deleteNotification(Input input);

}
