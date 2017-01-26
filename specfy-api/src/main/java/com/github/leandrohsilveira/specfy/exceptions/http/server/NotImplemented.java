package com.github.leandrohsilveira.specfy.exceptions.http.server;

import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.http.ServerError;

public class NotImplemented extends ServerError {

	private static final long serialVersionUID = 3332612598079524283L;

	public NotImplemented(Response response) {
		super(response);
	}

}
