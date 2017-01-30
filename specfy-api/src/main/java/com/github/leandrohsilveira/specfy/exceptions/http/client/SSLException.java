package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class SSLException extends ClientError {

	private static final long serialVersionUID = 6215103759328492309L;

	public SSLException(Throwable throwable) {
		super(throwable);
	}

}
