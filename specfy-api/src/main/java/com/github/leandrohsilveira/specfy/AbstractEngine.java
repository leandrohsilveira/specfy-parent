package com.github.leandrohsilveira.specfy;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import com.github.leandrohsilveira.specfy.exceptions.ClientSpecException;
import com.github.leandrohsilveira.specfy.exceptions.ValidationException;

public abstract class AbstractEngine implements Engine {

	public abstract Request createRequest(String url, Charset charset) throws IOException;

	@Override
	public Response send(RequestSpec requestSpec) throws ValidationException, ClientSpecException {
		String rawUrl = requestSpec.resource.resourceSpec.compose();
		Charset charset = requestSpec.resource.resourceSpec.client.charset;

		Map<String, List<Object>> headers = null;
		Map<String, List<Object>> cookies = null;

		try {
			for (Entry<String, ParameterSpec> paramSpecEntry : requestSpec.resource.resourceSpec.parameters.entrySet()) {
				String paramName = paramSpecEntry.getKey();
				ParameterSpec parameterSpec = paramSpecEntry.getValue();

				List<Object> values = getParameterValues(requestSpec, parameterSpec);

				sw: switch (parameterSpec.type) {
					case COOKIE:
						if (cookies == null) cookies = new HashMap<>();
						cookies.put(paramName, values);
						break sw;
					case HEADER:
						if (headers == null) headers = new HashMap<>();
						headers.put(paramName, values);
						break sw;
					case PATH:
						rawUrl = rawUrl.replace(String.format("{%s}", paramName), URLEncoder.encode(values.get(0).toString(), charset.name()));
						break sw;
					case QUERY:
					default:
						rawUrl = applyQueryParameters(rawUrl, paramName, values, charset);
						break;
				}

			}

			String url = rawUrl;

			Request request = createRequest(url, charset);

			request.setMethod(requestSpec.resource.method);

			applySslContext(requestSpec, request);

			applyHeaders(headers, request);

			applyCookies(cookies, request);

			applyBody(requestSpec, request);

			return request.getResponse();

		} catch (UnsupportedEncodingException e) {
			// TODO: handle
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void applySslContext(RequestSpec requestSpec, Request request) {
		if (requestSpec.sslContext != null) request.setSslContext(requestSpec.sslContext);
		else if (requestSpec.resource.resourceSpec.client.defaultSslContext != null) request.setSslContext(requestSpec.resource.resourceSpec.client.defaultSslContext);
	}

	private List<Object> getParameterValues(RequestSpec request, ParameterSpec parameterSpec) {
		List<Object> values = null;
		if (parameterSpec.fixed) {
			values = new ArrayList<>(Arrays.asList(parameterSpec.defaultValue));
		}

		List<Object> requestValues = request.parameters.get(parameterSpec.name);
		if (requestValues != null) {

			if (values != null) {
				values.addAll(requestValues);
			} else {
				values = requestValues;
			}

		} else if (values == null && parameterSpec.defaultValue != null) {
			values = Arrays.asList(parameterSpec.defaultValue);
		}
		return values;
	}

	private String applyQueryParameters(String rawUrl, String paramName, List<Object> values, Charset charset) throws UnsupportedEncodingException {
		if (values != null) for (Object value : values) {
			if (Pattern.matches("^.+\\?.+$", rawUrl)) {
				rawUrl = rawUrl.concat("&");
			} else {
				rawUrl = rawUrl.concat("?");
			}
			rawUrl = rawUrl.concat(paramName).concat("=").concat(URLEncoder.encode(value.toString(), charset.name()));
		}
		return rawUrl;
	}

	private void applyCookies(Map<String, List<Object>> cookies, Request request) {
		if (cookies != null) for (Entry<String, List<Object>> cookieEntry : cookies.entrySet()) {
			request.addCookieValues(cookieEntry.getKey(), cookieEntry.getValue());
		}
	}

	private void applyBody(RequestSpec requestSpec, Request request) throws IOException, UnsupportedEncodingException {
		if (requestSpec.resource.resourceSpec.bodySerializer != null || requestSpec.content != null) {
			request.setContentType(requestSpec.resource.resourceSpec.bodySerializer.getContentType());
			request.writeBody(requestSpec.resource.resourceSpec.bodySerializer, requestSpec.content);
		}
	}

	private void applyHeaders(Map<String, List<Object>> headers, Request request) {
		if (headers != null) for (Entry<String, List<Object>> headerEntry : headers.entrySet()) {
			request.addHeaderValues(headerEntry.getKey(), headerEntry.getValue());
		}
	}
}
