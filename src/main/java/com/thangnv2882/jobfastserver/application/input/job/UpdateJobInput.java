package com.thangnv2882.jobfastserver.application.input.job;

import com.thangnv2882.jobfastserver.application.constants.MessageConstant;
import com.thangnv2882.jobfastserver.application.utils.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateJobInput {

  @NotNull(message = MessageConstant.INVALID_SOME_THING_FIELD_IS_REQUIRED)
  private Long id;

  private String jobName;

  private Double salary;

  private String jobType;

  private String jobDescription;

  private String jobRequirements;

  private String timeUp;

  private int numberOfRecruitments;

  private Long categoryId;

  private String lastModifiedBy = SecurityUtil.getCurrentAccountLogin();

}
