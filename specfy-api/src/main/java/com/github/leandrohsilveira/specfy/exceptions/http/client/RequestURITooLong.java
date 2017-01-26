package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Request;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class RequestURITooLong extends ClientError {

	private static final long serialVersionUID = -879418795010850808L;

	public RequestURITooLong(Request request) {
		super(request);
	}

}
