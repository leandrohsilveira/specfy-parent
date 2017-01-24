package com.github.leandrohsilveira.specfy.exceptions;

public class ClientException extends Exception {

	private static final long serialVersionUID = 1921900593133793798L;

	public ClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public ClientException(String message) {
		super(message);
	}

	public ClientException(Throwable cause) {
		super(cause);
	}

}
