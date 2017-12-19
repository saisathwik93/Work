package com.credoop.exceptions;


/**
 * This class will notify Exception Handler that application is not registered
 * in our list.
 *
 *
 *
 * 
 */
public class AccessDeniedException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccessDeniedException(String message) {
		super(message);
	}

	public AccessDeniedException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public AccessDeniedException(Throwable throwable) {
		super(throwable);
	}
}
