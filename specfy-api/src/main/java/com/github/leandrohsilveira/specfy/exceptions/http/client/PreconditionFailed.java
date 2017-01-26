package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Request;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class PreconditionFailed extends ClientError {

	private static final long serialVersionUID = 825501413075179000L;

	public PreconditionFailed(Request request) {
		super(request);
	}

}
