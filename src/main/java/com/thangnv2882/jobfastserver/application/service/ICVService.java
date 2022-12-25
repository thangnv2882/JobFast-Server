package com.thangnv2882.jobfastserver.application.service;

import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.commons.PageMetaInput;
import com.thangnv2882.jobfastserver.application.input.cv.UpdateCVInput;
import com.thangnv2882.jobfastserver.application.input.cv.UploadCVInput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.output.common.UploadFileOutput;
import com.thangnv2882.jobfastserver.application.output.cv.GetListCVOutput;
import com.thangnv2882.jobfastserver.domain.entity.CV;

import java.io.IOException;
import java.util.List;

public interface ICVService {

  GetListCVOutput findAll(PageMetaInput input);

  CV findCVById(Input input);

  List<CV> findCVByAccount(Input input);

  UploadFileOutput uploadCV(UploadCVInput input) throws IOException;

  Output updateCV(UpdateCVInput input);

  Output deleteCV(Input input);

}
