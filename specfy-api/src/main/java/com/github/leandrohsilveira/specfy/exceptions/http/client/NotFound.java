package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.RequestSpec;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class NotFound extends ClientError {

	private static final long serialVersionUID = 5497971950470827109L;

	public NotFound(RequestSpec requestSpec) {
		super(requestSpec);
	}

}
