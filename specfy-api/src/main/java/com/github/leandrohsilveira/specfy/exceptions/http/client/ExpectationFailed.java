package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class ExpectationFailed extends ClientError {

	private static final long serialVersionUID = -9006164093099727944L;

	public ExpectationFailed(Response response) {
		super(response);
	}

}
