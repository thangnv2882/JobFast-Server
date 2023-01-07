package com.thangnv2882.jobfastserver.adapter.web.v1.controller;

import com.thangnv2882.jobfastserver.adapter.web.base.RestApiV1;
import com.thangnv2882.jobfastserver.adapter.web.base.VsResponseUtil;
import com.thangnv2882.jobfastserver.application.constants.UrlConstant;
import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.job.CreateJobInput;
import com.thangnv2882.jobfastserver.application.input.job.FindJobInput;
import com.thangnv2882.jobfastserver.application.input.job.UpdateJobInput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.output.job.GetListJobOutput;
import com.thangnv2882.jobfastserver.application.service.IJobService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestApiV1
public class JobController {

  private final IJobService jobService;

  public JobController(IJobService jobService) {
    this.jobService = jobService;
  }

  @Operation(summary = "API Find Job")
  @GetMapping(UrlConstant.Job.GET_ALL)
  public ResponseEntity<?> findJobs(@RequestBody FindJobInput input) {
    // Get output
    GetListJobOutput output = jobService.findAll(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @Operation(summary = "API Get Job By Id")
  @GetMapping(UrlConstant.Job.GET)
  public ResponseEntity<?> getJobById(@PathVariable("idJob") Long idJob) {
    // Create input
    Input input = new Input(idJob);
    // Return output
    return VsResponseUtil.ok(jobService.findJobById(input));
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Get Job Of User (APPLY and JOB)")
  @GetMapping(UrlConstant.Job.GET_BY_DETAIL_TYPE)
  public ResponseEntity<?> getJobOfUser() {
    return VsResponseUtil.ok(jobService.findJobOfUser());
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Create Job")
  @PostMapping(UrlConstant.Job.CREATE)
  public ResponseEntity<?> createJob(@Valid @RequestBody CreateJobInput input) {
    // Get output
    Output output = jobService.createJob(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Update Job")
  @PatchMapping(UrlConstant.Job.UPDATE)
  public ResponseEntity<?> updateJob(@Valid @RequestBody UpdateJobInput updateJobInput) {
    // Get output
    Output output = jobService.updateJob(updateJobInput);
    // Return output
    return VsResponseUtil.ok(output);
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
  @Operation(summary = "API Delete Job")
  @DeleteMapping(UrlConstant.Job.DELETE)
  public ResponseEntity<?> deleteJob(@PathVariable("idJob") Long idJob) {
    // Create input
    Input input = new Input(idJob);
    // Get output
    Output output = jobService.deleteJob(input);
    // Return output
    return VsResponseUtil.ok(output);
  }

}
