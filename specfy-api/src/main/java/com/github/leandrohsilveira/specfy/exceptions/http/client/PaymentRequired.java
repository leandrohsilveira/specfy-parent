package com.github.leandrohsilveira.specfy.exceptions.http.client;

import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;

public class PaymentRequired extends ClientError {

	private static final long serialVersionUID = -1881934606497778476L;

	public PaymentRequired(Response response) {
		super(response);
	}

}
