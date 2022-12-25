package com.thangnv2882.jobfastserver.application.input.account;

import com.thangnv2882.jobfastserver.application.constants.MessageConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeleteAccountInput {

  @NotNull(message = MessageConstant.INVALID_SOME_THING_FIELD_IS_REQUIRED)
  private Long id;

}
