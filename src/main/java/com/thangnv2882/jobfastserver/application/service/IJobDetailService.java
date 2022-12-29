package com.thangnv2882.jobfastserver.application.service;

import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.commons.PageMetaInput;
import com.thangnv2882.jobfastserver.application.input.job_detail.ApplyJobInput;
import com.thangnv2882.jobfastserver.application.input.job_detail.UpdateJobDetailInput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.output.job_detail.GetListJobDetailOutput;
import com.thangnv2882.jobfastserver.domain.entity.JobDetail;

import java.util.List;

public interface IJobDetailService {

  GetListJobDetailOutput findAll(PageMetaInput input);

  JobDetail findJobDetailById(Input input);

  List<JobDetail> findAllJobDetailAppliedByIdJob(Long idJob);

  Output applyJob(ApplyJobInput input);

  Output updateJobDetail(UpdateJobDetailInput input);

  Output deleteJobDetail(Input input);
}
