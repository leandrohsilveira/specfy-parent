package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class RequestEntityTooLarge extends ClientError {

	private static final long serialVersionUID = -3505616297932548037L;

	public RequestEntityTooLarge(Response response) {
		super(response);
	}

}
