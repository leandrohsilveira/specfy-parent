package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class PreconditionFailed extends ClientError {

	private static final long serialVersionUID = 825501413075179000L;

	public PreconditionFailed(Response response) {
		super(response);
	}

}
