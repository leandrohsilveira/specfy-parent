package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Request;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class MethodNotAllowed extends ClientError {

	private static final long serialVersionUID = 518704296579306091L;

	public MethodNotAllowed(Request request) {
		super(request);
	}

}
