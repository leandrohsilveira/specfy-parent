package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class Forbidden extends ClientError {

	private static final long serialVersionUID = -1826259683028359185L;

	public Forbidden(Response response) {
		super(response);
	}

}
