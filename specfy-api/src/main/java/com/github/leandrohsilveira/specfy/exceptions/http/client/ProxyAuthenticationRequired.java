package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Request;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class ProxyAuthenticationRequired extends ClientError {

	private static final long serialVersionUID = 7515368793759613895L;

	public ProxyAuthenticationRequired(Request request) {
		super(request);
	}

}
