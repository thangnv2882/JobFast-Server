package com.thangnv2882.jobfastserver.application.utils;

import javax.servlet.http.HttpServletRequest;

public class UrlUtil {

  public static String applicationUrl(HttpServletRequest request) {
    return "http://" +
        request.getServerName() +
        ":" +
        request.getServerPort() +
        request.getContextPath();
  }

}
