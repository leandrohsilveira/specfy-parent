package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.RequestSpec;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class NotAcceptable extends ClientError {

	private static final long serialVersionUID = -5494346740591705705L;

	public NotAcceptable(RequestSpec requestSpec) {
		super(requestSpec);
	}

}
