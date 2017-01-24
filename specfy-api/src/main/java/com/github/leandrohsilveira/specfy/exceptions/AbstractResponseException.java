package com.github.leandrohsilveira.specfy.exceptions;

import java.io.IOException;

import com.github.leandrohsilveira.specfy.Deserializer;
import com.github.leandrohsilveira.specfy.RESTfulClientSpec;
import com.github.leandrohsilveira.specfy.Response;

public abstract class AbstractResponseException extends Exception {

	private static final long serialVersionUID = -4737642975124215055L;

	public AbstractResponseException(String message, RESTfulClientSpec client, Response response) {
		super(message);
		this.client = client;
		this.response = response;
	}

	private Response response;
	private RESTfulClientSpec client;
	private Object deserializedResponseBody;

	public Response getResponse() {
		return response;
	}

	@SuppressWarnings("unchecked")
	public <T> T getDeserializedResponseBody(Class<T> returnType) throws IOException {
		if (this.deserializedResponseBody == null) {
			try (Response response = getResponse()) {
				Deserializer<?> deserializer = client.getDeserializer(returnType);
				if (deserializer == null) throw new IllegalArgumentException(String.format("No response body deserializer found for class %s", returnType.getName()));
				this.deserializedResponseBody = deserializer.deserialize(response.getBody());
			}
		}
		return (T) this.deserializedResponseBody;
	}

	public static void checkResponseStatus(int status) {
		if (status >= 400) {
			switch (status) {
				case 400:
					break;

				default:
					break;
			}
		}
	}

}
