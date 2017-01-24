package com.github.leandrohsilveira.specfy.exceptions;

public class ClientSpecException extends Exception {

	private static final long serialVersionUID = 1921900593133793798L;

	public ClientSpecException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientSpecException(String message) {
		super(message);
	}

	public ClientSpecException(Throwable cause) {
		super(cause);
	}

}
