package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.RequestSpec;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class Conflict extends ClientError {

	private static final long serialVersionUID = -4694145543224817723L;

	public Conflict(RequestSpec requestSpec) {
		super(requestSpec);
	}

}
