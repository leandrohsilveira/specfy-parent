package com.github.leandrohsilveira.specfy.engines.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import com.github.leandrohsilveira.specfy.AbstractRequest;
import com.github.leandrohsilveira.specfy.RequestMethod;
import com.github.leandrohsilveira.specfy.RequestSpec;
import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;
import com.github.leandrohsilveira.specfy.exceptions.http.ServerError;

public class NetURLConnectionRequest extends AbstractRequest {

	protected HttpURLConnection connection;

	public NetURLConnectionRequest(RequestSpec requestSpec, String url, Charset charset) throws IOException {
		super(requestSpec, charset);
		this.connection = (HttpURLConnection) new URL(url).openConnection();
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
	public OutputStream getBody() throws IOException {
		this.connection.setDoOutput(true);
		return this.connection.getOutputStream();
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

	public HttpURLConnection getConnection() {
		return connection;
	}

	@Override
	public Response createResponse() throws ClientError, ServerError {
		return new NetURLConnectionResponse(this.connection);
	}

	@Override
	public void close() {
		if (this.connection != null) this.connection.disconnect();
	}

}
