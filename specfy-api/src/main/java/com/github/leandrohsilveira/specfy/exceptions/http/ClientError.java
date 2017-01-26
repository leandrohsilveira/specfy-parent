package com.github.leandrohsilveira.specfy.exceptions.http;

import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.ResponseException;

public abstract class ClientError extends ResponseException {

	private static final long serialVersionUID = -4645346014882908377L;

	public ClientError(Response response) {
		super(response);
	}

}
