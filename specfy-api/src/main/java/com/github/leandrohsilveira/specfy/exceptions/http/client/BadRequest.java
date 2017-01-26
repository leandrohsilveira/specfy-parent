package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Request;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class BadRequest extends ClientError {

	private static final long serialVersionUID = -4170239776136618439L;

	public BadRequest(Request request) {
		super(request);
	}

}
