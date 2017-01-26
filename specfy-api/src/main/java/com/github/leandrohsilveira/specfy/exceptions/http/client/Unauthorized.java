package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Request;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class Unauthorized extends ClientError {

	private static final long serialVersionUID = -2720836500598865877L;

	public Unauthorized(Request request) {
		super(request);
	}

}
