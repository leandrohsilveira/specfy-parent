package com.github.leandrohsilveira.specfy;

import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import com.github.leandrohsilveira.specfy.exceptions.ClientException;

public interface Request extends Closeable {

	Charset getCharset();

	void setMethod(RequestMethod method) throws ClientException;

	void setContentType(String contentType);

	void writeFormParameter(String param) throws IOException;

	void writeSerializable(Serializer serializer, Object content) throws IOException;

	void addHeaderValues(String headerName, List<Object> headerValues);

	void addCookieValues(String key, List<Object> value);

	Response getResponse();

}
