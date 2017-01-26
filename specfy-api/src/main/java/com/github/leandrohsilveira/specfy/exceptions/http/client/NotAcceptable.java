package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class NotAcceptable extends ClientError {

	private static final long serialVersionUID = -5494346740591705705L;

	public NotAcceptable(Response response) {
		super(response);
	}

}
