package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class Gone extends ClientError {

	private static final long serialVersionUID = -6345014851648366846L;

	public Gone(Response response) {
		super(response);
	}

}
