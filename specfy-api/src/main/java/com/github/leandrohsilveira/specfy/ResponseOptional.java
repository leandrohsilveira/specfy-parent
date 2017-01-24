package com.github.leandrohsilveira.specfy;

import java.io.IOException;

public class ResponseOptional<S, E> {

	private RequestSpec requestSpec;
	private Class<S> success;
	private Class<E> error;

	protected ResponseOptional(RequestSpec requestSpec, Class<S> success, Class<E> error) {
		this.requestSpec = requestSpec;
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		int status = requestSpec.getResponse().getStatus();
		return status >= 200 && status < 400;
	}

	public S deserializeOnSuccess() throws IOException {
		if (isSuccess()) {
			return requestSpec.getDeserializedResponseBody(success);
		}
		return null;
	}

	public E deserializeOnError() throws IOException {
		if (!isSuccess()) {
			return requestSpec.getDeserializedResponseBody(error);
		}
		return null;
	}

}
