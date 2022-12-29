package com.thangnv2882.jobfastserver.application.input.account;

import com.thangnv2882.jobfastserver.application.constants.AccountType;
import com.thangnv2882.jobfastserver.application.input.commons.PageMetaInput;
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
