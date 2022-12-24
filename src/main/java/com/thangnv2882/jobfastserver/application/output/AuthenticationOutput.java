package com.thangnv2882.jobfastserver.application.output;

import com.thangnv2882.jobfastserver.domain.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationOutput {

  private Account account;

  private String token;

}
