package com.github.wesleyegberto.adoptajsr.jsfchat;

public class UsernameNotUniqueException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UsernameNotUniqueException(String message) {
		super(message);
	}

	public UsernameNotUniqueException(String message, Throwable cause) {
		super(message, cause);
	}

}
