package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.RequestSpec;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class UnsupportedMediaType extends ClientError {

	private static final long serialVersionUID = -702595656525486919L;

	public UnsupportedMediaType(RequestSpec requestSpec) {
		super(requestSpec);
	}

}
