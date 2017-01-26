package com.github.leandrohsilveira.specfy.exceptions.http.server;

import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.http.ServerError;

public class HTTPVersionNotSupported extends ServerError {

	private static final long serialVersionUID = 8982856400891499499L;

	public HTTPVersionNotSupported(Response response) {
		super(response);
	}

}
