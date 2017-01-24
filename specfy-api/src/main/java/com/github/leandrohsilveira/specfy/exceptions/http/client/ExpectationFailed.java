package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.RequestSpec;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class ExpectationFailed extends ClientError {

	private static final long serialVersionUID = -9006164093099727944L;

	public ExpectationFailed(RequestSpec requestSpec) {
		super(requestSpec);
	}

}
