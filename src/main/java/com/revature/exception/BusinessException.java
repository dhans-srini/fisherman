package com.revature.exception;

public class BusinessException extends Exception {

  private static final long serialVersionUID = 1L;

  public BusinessException() {}

  public BusinessException(String string) {}

  public BusinessException(Exception e) {}

  public BusinessException(String string, Exception e) {}
}
