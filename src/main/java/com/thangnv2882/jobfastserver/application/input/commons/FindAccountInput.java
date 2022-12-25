package com.thangnv2882.jobfastserver.application.input.commons;

import com.thangnv2882.jobfastserver.application.constants.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FindAccountInput extends PageMetaInput {

  private AccountType accountType;

  private String jobPosition;

  private Integer experience;

  private String gender;

  private String address;

}
