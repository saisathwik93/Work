package com.credo.users.exceptions;

public class UserNotFoundError {
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public UserNotFoundError(String message) {
		this.errorMessage=message;
	}
}
