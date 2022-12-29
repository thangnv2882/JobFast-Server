package com.thangnv2882.jobfastserver.application.output.job;

import com.thangnv2882.jobfastserver.application.output.common.PagingMeta;
import com.thangnv2882.jobfastserver.domain.entity.Category;
import com.thangnv2882.jobfastserver.domain.entity.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetListJobOutput {

  private List<Job> jobs;

  private PagingMeta meta;

}
