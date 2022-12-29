package com.thangnv2882.jobfastserver.application.service;

import com.thangnv2882.jobfastserver.application.input.commons.Input;
import com.thangnv2882.jobfastserver.application.input.job.CreateJobInput;
import com.thangnv2882.jobfastserver.application.input.job.FindJobInput;
import com.thangnv2882.jobfastserver.application.input.job.UpdateJobInput;
import com.thangnv2882.jobfastserver.application.output.common.Output;
import com.thangnv2882.jobfastserver.application.output.job.GetListJobOutput;
import com.thangnv2882.jobfastserver.domain.entity.Job;

import java.util.List;
import java.util.Map;

public interface IJobService {

  GetListJobOutput findAll(FindJobInput input);

  Job findJobById(Input input);

  Map<String, List<Job>> findJobOfUser();

  Output createJob(CreateJobInput input);

  Output updateJob(UpdateJobInput input);

  Output deleteJob(Input input);


}
