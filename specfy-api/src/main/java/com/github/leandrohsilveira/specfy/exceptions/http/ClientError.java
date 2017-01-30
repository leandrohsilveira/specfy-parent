package com.github.leandrohsilveira.specfy.exceptions.http;

import com.github.leandrohsilveira.specfy.Request;
import com.github.leandrohsilveira.specfy.exceptions.ResponseException;

public abstract class ClientError extends ResponseException {

	private static final long serialVersionUID = -4645346014882908377L;

	public ClientError(Request request) {
		super(request);
	}

	public ClientError(Throwable throwable) {
		super(throwable);
	}

}
