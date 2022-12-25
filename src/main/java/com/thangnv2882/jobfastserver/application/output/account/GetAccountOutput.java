package com.thangnv2882.jobfastserver.application.output.account;

import com.thangnv2882.jobfastserver.application.constants.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAccountOutput {

  private String email;

  private String name;

  private AccountType accountType;

  private String phoneNumber;

  private String jobPosition;

  private String birthday;

  private String address;

  private String avatar;

  private Integer experience;

  private String about;

  private String gender;

  private boolean enable;

}
