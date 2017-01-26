package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class MethodNotAllowed extends ClientError {

	private static final long serialVersionUID = 518704296579306091L;

	public MethodNotAllowed(Response response) {
		super(response);
	}

}
