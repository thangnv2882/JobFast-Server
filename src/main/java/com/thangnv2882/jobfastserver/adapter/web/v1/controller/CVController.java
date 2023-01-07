package com.thangnv2882.jobfastserver.adapter.web.v1.controller;

import com.thangnv2882.jobfastserver.adapter.web.base.RestApiV1;
import com.thangnv2882.jobfastserver.adapter.web.base.VsResponseUtil;
import com.thangnv2882.jobfastserver.application.constants.UrlConstant;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.commons.PageMetaInput;
import com.thangnv2882.jobfastserver.application.input.cv.UpdateCVInput;
import com.thangnv2882.jobfastserver.application.input.cv.UploadCVInput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.output.common.UploadFileOutput;
import com.thangnv2882.jobfastserver.application.output.cv.GetListCVOutput;
import com.thangnv2882.jobfastserver.application.service.ICVService;
import com.thangnv2882.jobfastserver.application.utils.UrlUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@RestApiV1
public class CVController {

  private final ICVService cvService;

  public CVController(ICVService cvService) {
    this.cvService = cvService;
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Get List CV")
  @GetMapping(UrlConstant.CV.GET_ALL)
  public ResponseEntity<?> getCVs(@RequestBody(required = false) PageMetaInput pageMetaInput) {
    // Get output
    GetListCVOutput output = cvService.findAll(pageMetaInput);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @Operation(summary = "API Get CV By Id")
  @GetMapping(UrlConstant.CV.GET)
  public ResponseEntity<?> getCVById(@PathVariable("idCV") Long idCV) {
    // Create input
    Input input = new Input(idCV);
    // Return output
    return VsResponseUtil.ok(cvService.findCVById(input));
  }

  @Operation(summary = "API Get CV By Account")
  @GetMapping(UrlConstant.CV.GET_BY_ACCOUNT)
  public ResponseEntity<?> getCVByAccount(@PathVariable("idAccount") Long idAccount) {
    // Create input
    Input input = new Input(idAccount);
    // Return output
    return VsResponseUtil.ok(cvService.findCVByAccount(input));
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Upload CV")
  @PostMapping(UrlConstant.CV.UPLOAD_CV)
  public ResponseEntity<?> uploadCV(@ModelAttribute UploadCVInput input,
                                    final HttpServletRequest request) {
    input.setApplicationUrl(UrlUtil.applicationUrl(request));
    // Get output
    UploadFileOutput output;
    try {
      output = cvService.uploadCV(input);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    // Return output
    return VsResponseUtil.ok(output);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Update CV")
  @PatchMapping(UrlConstant.CV.UPDATE)
  public ResponseEntity<?> updateCV(@Valid @RequestBody UpdateCVInput input) {
    // Get output
    Output output = cvService.updateCV(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Delete CV")
  @DeleteMapping(UrlConstant.CV.DELETE)
  public ResponseEntity<?> deleteCV(@PathVariable("idCV") Long idCV) {
    // Create input
    Input input = new Input(idCV);
    // Get output
    Output output = cvService.deleteCV(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

}
