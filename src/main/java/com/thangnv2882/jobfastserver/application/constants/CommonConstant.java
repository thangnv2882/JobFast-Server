package com.thangnv2882.jobfastserver.application.constants;

import java.text.SimpleDateFormat;

public class CommonConstant {

  public static final String FORMAT_DATE_PATTERN = "dd/MM/yyyy";
  public static final SimpleDateFormat FORMAT_DATE = new SimpleDateFormat(FORMAT_DATE_PATTERN);
  public static final String FORMAT_DATE_DETAIL_PATTERN = "dd/MM/yyyy HH:mm:ss";
  public static final SimpleDateFormat FORMAT_DATE_DETAIL = new SimpleDateFormat(FORMAT_DATE_DETAIL_PATTERN);

  public static final String EMPTY_STRING = "";
  public static final boolean TRUE = true;
  public static final boolean FALSE = false;
  public static final String SUCCESS = "Successful";
  public static final String IMAGES = "static/images";
  public static final String URL_IMAGES = "/images/";
  public static final String CVS = "static/cv";
  public static final String URL_CV = "/cv/";
  public static final int PAGE_SIZE_DEFAULT = 20;
  public static final String SORT_BY_DEFAULT = "id";
  public static final String SORT_TYPE_ASC = "ASC";
  public static final String SORT_TYPE_DESC = "DESC";
}
