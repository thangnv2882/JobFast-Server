package com.thangnv2882.jobfastserver.application.output.common;

import com.thangnv2882.jobfastserver.application.constants.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagingMeta {

  private Long total;

  private Integer pageNum;

  private Integer pageSize;

  private String sortBy;

  private String sortType;

  private Integer totalPage;

  public PagingMeta(Long total, Integer pageNum, Integer pageSize, String sortBy, String sortType) {
    this.total = total;
    this.pageSize = pageSize;
    this.pageNum = getPageNum(pageNum);
    this.sortType = sortType;
    this.sortBy = sortBy;
    this.totalPage = getTotalPage();
  }

  public Integer getPageNum(int pageNum) {
    if ((long) pageNum * pageSize > total) {
      pageNum = (int) Math.ceil(total * 1.0 / pageSize);
    }
    return pageNum;
  }

  public Integer getTotalPage() {
    return (int) Math.ceil(total * 1.0 / pageSize);
  }

}