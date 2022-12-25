package com.thangnv2882.jobfastserver.application.input.commons;

import com.thangnv2882.jobfastserver.application.constants.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageMetaInput {

  private int pageNum;

  private int pageSize;

  private String sortType;

  private String sortBy;

  public int getPageNum() {
    if (pageNum <= 0) {
      pageNum = 0;
    }
    return pageNum;
  }

  public int getPageSize() {
    if (pageSize <= 0) {
      pageSize = CommonConstant.PAGE_SIZE_DEFAULT;
    }
    return pageSize;
  }

  public String getSortType() {
    if (sortType == null) {
      return CommonConstant.SORT_TYPE_ASC;
    }
    return sortType;
  }

  public String getSortBy() {
    if (sortBy == null) {
      return CommonConstant.SORT_BY_DEFAULT;
    }
    return sortBy;
  }

}
