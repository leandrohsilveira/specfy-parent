package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Request;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class UnsupportedMediaType extends ClientError {

	private static final long serialVersionUID = -702595656525486919L;

	public UnsupportedMediaType(Request request) {
		super(request);
	}

}
