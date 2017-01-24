package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.RequestSpec;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class Forbidden extends ClientError {

	private static final long serialVersionUID = -1826259683028359185L;

	public Forbidden(RequestSpec requestSpec) {
		super(requestSpec);
	}

}
