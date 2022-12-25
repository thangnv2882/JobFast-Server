package com.thangnv2882.jobfastserver.application.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileOutput {

  private boolean status;

  private String message;

  private String path;

}
