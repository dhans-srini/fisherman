package com.revature.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessException() {
	}

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(Exception e) {
		super(e);
	}

	public BusinessException(String mgs, Exception e) {
		super(mgs, e);
	}
}
