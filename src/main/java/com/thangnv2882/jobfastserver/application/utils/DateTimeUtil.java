package com.thangnv2882.jobfastserver.application.utils;

import com.thangnv2882.jobfastserver.application.constants.CommonConstant;

import java.text.ParseException;
import java.util.Date;

public class DateTimeUtil {

  public static String getDateTimeNow() {
    return CommonConstant.FORMAT_DATE.format(new Date());
  }

  public static String getDateTimeNowDetail() {
    return CommonConstant.FORMAT_DATE_DETAIL.format(new Date());
  }

  public static Date getDateByString(String str) throws ParseException {
    return CommonConstant.FORMAT_DATE_DETAIL.parse(str);
  }

}
