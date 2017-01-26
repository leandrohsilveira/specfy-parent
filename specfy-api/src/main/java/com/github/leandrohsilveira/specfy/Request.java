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

	Response getResponse() throws ClientError, ServerError;

	RequestSpec getRequestSpec();

	<T> T getResponseEntity(Class<T> clazz) throws ClientError, ServerError, IOException;

	Request serialize(Object object) throws IOException;

}
