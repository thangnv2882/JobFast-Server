package com.thangnv2882.jobfastserver.application.input.auth;

import com.thangnv2882.jobfastserver.application.constants.MessageConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthenticationRequest {

  @NotBlank(message = MessageConstant.INVALID_SOME_THING_FIELD_IS_REQUIRED)
  private String email;

  @NotBlank(message = MessageConstant.INVALID_SOME_THING_FIELD_IS_REQUIRED)
  private String password;
}
