package com.cg.dd.app.exceptions;

/**
 * Exception to handle if user already exits.
 * 
 * 
 *
 */
public class UserExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserExistsException() {
		super();
	}

	public UserExistsException(String message) {
		super(message);
	}

}
