package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.RequestSpec;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class RequestURITooLong extends ClientError {

	private static final long serialVersionUID = -879418795010850808L;

	public RequestURITooLong(RequestSpec requestSpec) {
		super(requestSpec);
	}

}
