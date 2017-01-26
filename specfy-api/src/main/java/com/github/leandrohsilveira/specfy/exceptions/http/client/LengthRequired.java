package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Request;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class LengthRequired extends ClientError {

	private static final long serialVersionUID = -7580036261972559084L;

	public LengthRequired(Request request) {
		super(request);
	}

}
