package com.thangnv2882.jobfastserver.adapter.web.base;

import com.fasterxml.jackson.annotation.JsonInclude;

public class RestData<T> {

  public RestStatus status;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private T data;

  public RestData() {
  }

  public RestData(T data) {
    this.status = RestStatus.SUCCESS;
    this.data = data;
  }

  public RestData(RestStatus status, T data) {
    this.status = status;
    this.data = data;
  }

  public RestData(RestStatus status, String message, T data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public static RestData<?> error(String message) {
    return new RestData<>(RestStatus.ERROR, message, null);
  }

  public RestStatus getStatus() {
    return status;
  }

  public void setStatus(RestStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}