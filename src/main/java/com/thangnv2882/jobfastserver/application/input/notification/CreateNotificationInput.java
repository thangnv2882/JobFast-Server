package com.thangnv2882.jobfastserver.application.input.notification;

import com.thangnv2882.jobfastserver.application.constants.MessageConstant;
import com.thangnv2882.jobfastserver.application.utils.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateNotificationInput {

  @NotBlank(message = MessageConstant.INVALID_SOME_THING_FIELD_IS_REQUIRED)
  private String title;

  @NotBlank(message = MessageConstant.INVALID_SOME_THING_FIELD_IS_REQUIRED)
  private String content;

  @NotBlank(message = MessageConstant.INVALID_SOME_THING_FIELD_IS_REQUIRED)
  private String path;

  private Boolean isRead = Boolean.FALSE;

  @NotNull(message = MessageConstant.INVALID_SOME_THING_FIELD_IS_REQUIRED)
  private Long accountId;

  private String createdBy = SecurityUtil.getCurrentAccountLogin();

  private String lastModifiedBy = SecurityUtil.getCurrentAccountLogin();

}
