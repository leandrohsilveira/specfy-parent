package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Request;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class Gone extends ClientError {

	private static final long serialVersionUID = -6345014851648366846L;

	public Gone(Request request) {
		super(request);
	}

}
