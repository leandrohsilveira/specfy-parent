package com.github.leandrohsilveira.specfy.exceptions.http;

import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.ResponseException;

public class ServerError extends ResponseException {

	private static final long serialVersionUID = 6510566908671463305L;

	public ServerError(Response response) {
		super(response);
	}

}
