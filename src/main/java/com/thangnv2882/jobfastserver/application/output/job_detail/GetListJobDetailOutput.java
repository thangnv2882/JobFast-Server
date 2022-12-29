package com.thangnv2882.jobfastserver.application.output.job_detail;

import com.thangnv2882.jobfastserver.application.output.common.PagingMeta;
import com.thangnv2882.jobfastserver.domain.entity.JobDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetListJobDetailOutput {

  private List<JobDetail> jobDetails;

  private PagingMeta meta;

}
