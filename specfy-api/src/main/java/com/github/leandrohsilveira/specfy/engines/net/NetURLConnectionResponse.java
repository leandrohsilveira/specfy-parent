package com.github.leandrohsilveira.specfy.engines.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import com.github.leandrohsilveira.specfy.Header;
import com.github.leandrohsilveira.specfy.Response;

public class NetURLConnectionResponse implements Response {

	private int status;
	private String statusText;
	private InputStream body;
	private Map<String, List<String>> headers;
	private HttpURLConnection connection;

	public NetURLConnectionResponse(HttpURLConnection connection) {
		try {
			this.connection = connection;
			this.connection.connect();
			this.status = this.connection.getResponseCode();
			this.statusText = this.connection.getResponseMessage();
			this.headers = this.connection.getHeaderFields();
			this.body = this.connection.getInputStream();
		} catch (IOException e) {
			this.body = this.connection.getErrorStream();
		}
	}

	@Override
	public List<String> getHeader(Header header) {
		return headers.get(header.getHeaderName());
	}

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public String getStatusText() {
		return statusText;
	}

	@Override
	public InputStream getBody() {
		return body;
	}

	@Override
	public void close() {
		if (this.connection != null) this.connection.disconnect();
	}

}