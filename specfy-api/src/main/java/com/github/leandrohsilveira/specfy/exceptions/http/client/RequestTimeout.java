package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Request;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class RequestTimeout extends ClientError {

	private static final long serialVersionUID = -8735554518935299417L;

	public RequestTimeout(Request request) {
		super(request);
	}

}
