package com.thangnv2882.jobfastserver.application.input.notification;

import com.thangnv2882.jobfastserver.application.constants.MessageConstant;
import com.thangnv2882.jobfastserver.application.utils.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SendNotificationInput {

  @NotNull(message = MessageConstant.INVALID_SOME_THING_FIELD_IS_REQUIRED)
  private Long notificationId;

  @NotNull(message = MessageConstant.INVALID_SOME_THING_FIELD_IS_REQUIRED)
  private Long[] accountId;

}
