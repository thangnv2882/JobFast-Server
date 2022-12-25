package com.thangnv2882.jobfastserver.application.input.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeAvatarInput {

  private MultipartFile avatar;

  private String applicationUrl;

}
