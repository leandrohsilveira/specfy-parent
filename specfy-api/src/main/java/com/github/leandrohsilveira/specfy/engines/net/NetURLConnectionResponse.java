package com.github.leandrohsilveira.specfy.engines.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map;

import com.github.leandrohsilveira.specfy.Header;
import com.github.leandrohsilveira.specfy.Response;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;
import com.github.leandrohsilveira.specfy.exceptions.http.ServerError;
import com.github.leandrohsilveira.specfy.exceptions.http.client.SSLException;
import com.github.leandrohsilveira.specfy.exceptions.http.server.ServiceUnavailable;

public class NetURLConnectionResponse implements Response {

	private int status;
	private String statusText;
	private InputStream body;
	private Map<String, List<String>> headers;
	private HttpURLConnection connection;

	public NetURLConnectionResponse(HttpURLConnection connection) throws ClientError, ServerError {
		try {
			this.connection = connection;
			this.connection.connect();
			this.status = this.connection.getResponseCode();
			this.statusText = this.connection.getResponseMessage();
			this.headers = this.connection.getHeaderFields();
			this.body = this.connection.getInputStream();
		} catch (ConnectException e) {
			throw new ServiceUnavailable(e);
		} catch (javax.net.ssl.SSLException e) {
			throw new SSLException(e);
		} catch (IOException e) {
			this.body = this.connection.getErrorStream();
		}
	}

	@Override
	public List<String> getHeader(Header header) {
		return headers.get(header.getHeaderName());
	}

	@Override
	public String getHeader(Header header, int index) {
		List<String> headerValues = getHeader(header);
		if (headerValues != null && headerValues.size() > index) {
			return headerValues.get(index);
		}
		return null;
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