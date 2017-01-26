package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class RequestTimeout extends ClientError {

	private static final long serialVersionUID = -8735554518935299417L;

	public RequestTimeout(Response response) {
		super(response);
	}

}
