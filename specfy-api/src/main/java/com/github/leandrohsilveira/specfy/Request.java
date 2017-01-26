package com.github.leandrohsilveira.specfy;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;
import com.github.leandrohsilveira.specfy.exceptions.http.ServerError;

public interface Request extends Closeable {

	Charset getCharset();

	OutputStream getBody() throws IOException;

	Request send() throws ClientError, ServerError;

	Response getResponse();

	RequestSpec getRequestSpec();

	<T> T getResponseEntity(Class<T> clazz) throws IOException;

	Request serialize(Object object) throws IOException;

	boolean isSent();

}
