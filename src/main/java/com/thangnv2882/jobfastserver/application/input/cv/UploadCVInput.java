package com.thangnv2882.jobfastserver.application.input.cv;


import com.thangnv2882.jobfastserver.application.constants.MessageConstant;
import com.thangnv2882.jobfastserver.application.utils.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadCVInput {

  private String name;

  @NotNull(message = MessageConstant.INVALID_SOME_THING_FIELD_IS_REQUIRED)
  private MultipartFile file;

  private String applicationUrl;

  private String createdBy = SecurityUtil.getCurrentAccountLogin();

  private String lastModifiedBy = SecurityUtil.getCurrentAccountLogin();

}
