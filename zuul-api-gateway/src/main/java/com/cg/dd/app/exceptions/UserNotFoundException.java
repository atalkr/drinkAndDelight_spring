package com.cg.dd.app.exceptions;

/**
 * Exception to handle if user doesnot exits.
 *
 */
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String message) {
		super(message);
	}
	
}
