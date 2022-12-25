package com.thangnv2882.jobfastserver.application.output.cv;

import com.thangnv2882.jobfastserver.application.output.common.PagingMeta;
import com.thangnv2882.jobfastserver.domain.entity.CV;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetListCVOutput {

  private List<CV> cvs;

  private PagingMeta meta;

}
