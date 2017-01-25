package com.github.leandrohsilveira.specfy.engines.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import com.github.leandrohsilveira.specfy.Request;
import com.github.leandrohsilveira.specfy.RequestMethod;
import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.Serializer;

public class NetURLConnectionRequest implements Request {

	protected Charset charset;
	protected HttpURLConnection connection;

	public NetURLConnectionRequest(String url, Charset charset) throws IOException {
		this.connection = (HttpURLConnection) new URL(url).openConnection();
		this.charset = charset;
	}

	@Override
	public Charset getCharset() {
		return this.charset;
	}

	@Override
	public void setSslContext(SSLContext sslContext) {
		if (this.connection instanceof HttpsURLConnection) {
			((HttpsURLConnection) this.connection).setSSLSocketFactory(sslContext.getSocketFactory());
		}
	}

	@Override
	public void setMethod(RequestMethod method) throws IOException {
		this.connection.setRequestMethod(method.name());
	}

	@Override
	public void setContentType(String contentType) {
		this.connection.setRequestProperty("Content-Type", contentType);
	}

	@Override
	public void writeBody(Serializer serializer, Object content) throws IOException {
		this.connection.setDoOutput(true);
		serializer.serialize(content, this.connection.getOutputStream(), charset);
	}

	@Override
	public void addHeaderValues(String headerName, List<Object> headerValues) {
		for (Object headerValue : headerValues) {
			this.connection.addRequestProperty(headerName, headerValue.toString());
		}
	}

	@Override
	public void addCookieValues(String cookieName, List<Object> cookieValues) {
		for (Object cookieValue : cookieValues) {
			this.connection.addRequestProperty("Cookie", String.format("%s=%s", cookieName, cookieValue));
		}
	}

	@Override
	public Response getResponse() {
		return new NetURLConnectionResponse(this.connection);
	}

	@Override
	public void close() {
		if (this.connection != null) this.connection.disconnect();
	}

}
