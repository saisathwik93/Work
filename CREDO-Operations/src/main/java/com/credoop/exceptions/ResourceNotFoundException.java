package com.credoop.exceptions;

/**
 * This class will notify Exception Handler that requested server resource is
 * not available.
 *
 *
 *
 * 
 */
public class ResourceNotFoundException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ResourceNotFoundException(Throwable throwable) {
		super(throwable);
	}
}
