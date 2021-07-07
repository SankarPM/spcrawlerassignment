package com.sankar.assignment.exception;

public class TechException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String errorDescription;

	String errorCode;

	public TechException(String errorCode, String errorDescription) {
		super();
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
}
