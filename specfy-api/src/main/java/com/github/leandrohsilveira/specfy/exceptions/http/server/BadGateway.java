package com.github.leandrohsilveira.specfy.exceptions.http.server;

import com.github.leandrohsilveira.specfy.Request;
import com.github.leandrohsilveira.specfy.exceptions.http.ServerError;

public class BadGateway extends ServerError {

	private static final long serialVersionUID = 911878168243996796L;

	public BadGateway(Request request) {
		super(request);
	}

}
