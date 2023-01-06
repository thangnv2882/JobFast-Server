package com.thangnv2882.jobfastserver.adapter.web.v1.controller;

import com.thangnv2882.jobfastserver.adapter.web.base.RestApiV1;
import com.thangnv2882.jobfastserver.adapter.web.base.VsResponseUtil;
import com.thangnv2882.jobfastserver.application.constants.UrlConstant;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.commons.PageMetaInput;
import com.thangnv2882.jobfastserver.application.input.job_detail.ApplyJobInput;
import com.thangnv2882.jobfastserver.application.input.job_detail.UpdateJobDetailInput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.output.job_detail.GetListJobDetailOutput;
import com.thangnv2882.jobfastserver.application.service.IJobDetailService;
import com.thangnv2882.jobfastserver.domain.entity.JobDetail;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestApiV1
public class JobDetailController {

  private final IJobDetailService jobDetailService;

  public JobDetailController(IJobDetailService jobDetailService) {
    this.jobDetailService = jobDetailService;
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Get All Job Detail")
  @GetMapping(UrlConstant.JobDetail.GET_ALL)
  public ResponseEntity<?> getJobDetails(@RequestBody PageMetaInput input) {
    // Get output
    GetListJobDetailOutput output = jobDetailService.findAll(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Get Job Detail")
  @GetMapping(UrlConstant.JobDetail.GET)
  public ResponseEntity<?> getJobDetailById(@PathVariable("idJobDetail") Long idJobDetail) {
    // Create input
    Input input = new Input(idJobDetail);
    // Return output
    return VsResponseUtil.ok(jobDetailService.findJobDetailById(input));
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Get Job Detail Applied By id Job")
  @GetMapping(UrlConstant.JobDetail.GET_APPLIED_BY_JOB)
  public ResponseEntity<?> getAllJobDetailAppliedByIdJob(@PathVariable("idJob") Long idJob) {
    // Get output
    List<JobDetail> jobDetails = jobDetailService.findAllJobDetailAppliedByIdJob(idJob);
    // Return output
    return VsResponseUtil.ok(jobDetails);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Apply To Job")
  @PostMapping(UrlConstant.JobDetail.APPLY_JOB)
  public ResponseEntity<?> applyJob(@RequestBody ApplyJobInput input) {
    // Get output
    Output output = jobDetailService.applyJob(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Update Job Detail")
  @PatchMapping(UrlConstant.JobDetail.UPDATE)
  public ResponseEntity<?> updateJobDetail(@RequestBody UpdateJobDetailInput input) {
    // Get output
    Output output = jobDetailService.updateJobDetail(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Delete Job Detail")
  @DeleteMapping(UrlConstant.JobDetail.DELETE)
  public ResponseEntity<?> deleteJobDetail(@PathVariable("idJobDetail") Long idJobDetail) {
    // Create input
    Input input = new Input(idJobDetail);
    // Get output
    Output output = jobDetailService.deleteJobDetail(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

}
