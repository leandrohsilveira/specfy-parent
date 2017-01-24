package com.github.leandrohsilveira.specfy;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import javax.net.ssl.SSLContext;

import com.github.leandrohsilveira.specfy.exceptions.ClientException;

public interface Request extends Closeable {

	Charset getCharset();

	void setSslContext(SSLContext sslContext);

	void setMethod(RequestMethod method) throws ClientException;

	void setContentType(String contentType);

	void writeBody(Serializer serializer, Object content) throws IOException;

	void addHeaderValues(String headerName, List<Object> headerValues);

	void addCookieValues(String key, List<Object> value);

	Response getResponse();

}
