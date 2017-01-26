package com.github.leandrohsilveira.specfy.exceptions.http.server;

import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.http.ServerError;

public class GatewayTimeout extends ServerError {

	private static final long serialVersionUID = -1721535457014760728L;

	public GatewayTimeout(Response response) {
		super(response);
	}

}
