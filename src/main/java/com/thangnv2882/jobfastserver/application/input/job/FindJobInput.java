package com.thangnv2882.jobfastserver.application.input.job;

import com.thangnv2882.jobfastserver.application.constants.JobLanguageEnum;
import com.thangnv2882.jobfastserver.application.constants.JobTypeEnum;
import com.thangnv2882.jobfastserver.application.input.commons.PageMetaInput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindJobInput extends PageMetaInput {

  private String jobName;

  private Double salary;

  private String location;

  private JobTypeEnum jobType;

  private JobLanguageEnum jobLanguage;

}
