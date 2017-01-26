package com.github.leandrohsilveira.specfy.exceptions.http.server;

import com.github.leandrohsilveira.specfy.Request;
import com.github.leandrohsilveira.specfy.exceptions.http.ServerError;

public class InternalServerError extends ServerError {

	private static final long serialVersionUID = -5856890398188688774L;

	public InternalServerError(Request request) {
		super(request);
	}

}
