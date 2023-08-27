package com.user.management.hackthon.exception;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;


	public UserNotFoundException() {
        super();
    }
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

	@Override
	public String toString() {
		return "UserNotFoundException [message=" + message + "]";
	}
}
