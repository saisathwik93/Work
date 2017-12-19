package com.credoop.exceptions;

/**
 * 
 * This class serves as main application level exception class, extend this
 * class if any need arise to create more specific level of exception.
 *
 */
public class ApplicationException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String errorCode = "UNKNOWN";

	public ApplicationException(String message, Throwable throwable, String errorCode) {
		super(String.format("ErrorCode : [%s], %s", errorCode, message), throwable);
		this.errorCode = errorCode;
	}

	public ApplicationException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ApplicationException(Throwable throwable) {
		super(throwable);
	}

	public ApplicationException(String message) {
		super(message);
	}

	public String getErrorCode() {
		return errorCode;
	}
}