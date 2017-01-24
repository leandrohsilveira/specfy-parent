package com.github.leandrohsilveira.specfy.exceptions.http.server;

import com.github.leandrohsilveira.specfy.RequestSpec;
import com.github.leandrohsilveira.specfy.exceptions.http.ServerError;

public class ServiceUnavailable extends ServerError {

	private static final long serialVersionUID = 2082122711228823246L;

	public ServiceUnavailable(RequestSpec requestSpec) {
		super(requestSpec);
	}

}
