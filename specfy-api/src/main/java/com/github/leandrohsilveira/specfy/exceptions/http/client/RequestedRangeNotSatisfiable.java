package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.RequestSpec;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class RequestedRangeNotSatisfiable extends ClientError {

	private static final long serialVersionUID = 7315408854835556154L;

	public RequestedRangeNotSatisfiable(RequestSpec requestSpec) {
		super(requestSpec);
	}

}
