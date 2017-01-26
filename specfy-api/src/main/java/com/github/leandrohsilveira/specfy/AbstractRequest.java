package com.github.leandrohsilveira.specfy;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import javax.net.ssl.SSLContext;

import com.github.leandrohsilveira.specfy.exceptions.ResponseException;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;
import com.github.leandrohsilveira.specfy.exceptions.http.ServerError;

public abstract class AbstractRequest implements Request {

	public AbstractRequest(RequestSpec requestSpec, Charset charset) {
		this.requestSpec = requestSpec;
		this.charset = charset;
	}

	private RequestSpec requestSpec;
	private Response response;
	private Charset charset;

	public abstract void setSslContext(SSLContext sslContext);

	public abstract void setMethod(RequestMethod method) throws IOException;

	public abstract void setContentType(String contentType);

	public abstract void addHeaderValues(String headerName, List<Object> headerValues);

	public abstract void addCookieValues(String key, List<Object> value);

	public abstract Response createResponse();

	@Override
	public Request serialize(Object object) throws IOException {
		if (object != null) {
			Class<?> clazz = object.getClass();
			Serializer serializer = requestSpec.resourceActionSpec.resourceSpec.client.getSerializer(clazz);
			if (serializer == null) throw new IllegalArgumentException("No serializer found for object of class " + clazz.getName());
			serializer.serialize(object, getBody(), charset);
		}
		return this;
	}

	@Override
	public RequestSpec getRequestSpec() {
		return requestSpec;
	}

	@Override
	public Charset getCharset() {
		return this.charset;
	}

	@Override
	public <T> T getResponseEntity(Class<T> clazz) throws ClientError, ServerError, IOException {
		Deserializer deserializer = requestSpec.resourceActionSpec.resourceSpec.client.getDeserializer(clazz);
		if (deserializer == null) throw new IllegalArgumentException("No deserializer found for object of class " + clazz.getName());
		return deserializer.deserialize(getResponse().getBody(), clazz, charset);
	}

	@Override
	public Response getResponse() throws ClientError, ServerError {
		if (response == null) send();
		return response;
	}

	@Override
	public Request send() throws ClientError, ServerError {
		if (response != null) throw new IllegalStateException("The request already been sent and can't be sent again.");
		response = createResponse();
		ResponseException.checkResponseStatus(response);
		return this;
	}
}
