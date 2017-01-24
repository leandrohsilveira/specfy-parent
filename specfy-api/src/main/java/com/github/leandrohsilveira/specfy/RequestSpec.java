package com.github.leandrohsilveira.specfy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;

import com.github.leandrohsilveira.specfy.exceptions.ClientException;
import com.github.leandrohsilveira.specfy.exceptions.ClientSideValidationException;
import com.github.leandrohsilveira.specfy.exceptions.ClientSideValidationException.Detail;
import com.github.leandrohsilveira.specfy.exceptions.ClientSideValidationException.Detail.Failure;

public class RequestSpec {

	protected RequestSpec(ResourceActionSpec resource) {
		super();
		this.resource = resource;
	}

	protected ResourceActionSpec resource;

	protected Map<String, List<Object>> parameters;

	protected Object content;

	protected SSLContext sslContext;

	protected boolean sent = false;

	private Response response;

	private Object deserializedResponseBody;

	public RequestSpec useSsl(SSLContext sslContext) {
		this.sslContext = sslContext;
		return this;
	}

	public RequestSpec bind(String parameterName, Object parameterValue) {
		if (this.resource.resourceSpec.parameters == null || this.resource.resourceSpec.parameters.isEmpty())
			throw new IllegalArgumentException(String.format("There is no parameter in client specification.", parameterName));
		ParameterSpec parameterSpec = this.resource.resourceSpec.parameters.get(parameterName);
		if (parameterSpec == null) throw new IllegalArgumentException(String.format("The \"%s\" parameter isn't in client specification.", parameterName));

		if (parameterValue != null) {
			if (this.parameters == null) this.parameters = new HashMap<>();
			if (this.parameters.containsKey(parameterName)) this.parameters.get(parameterName).add(parameterValue);
			else
				this.parameters.put(parameterName, new ArrayList<>(Arrays.asList(parameterValue)));
		}
		return this;
	}

	public RequestSpec body(Object content) {
		if (resource.resourceSpec.bodySerializer == null) throw new IllegalArgumentException("The resource spec don't defines a body serializer and shouldn't write an body content.");
		this.content = content;
		return this;
	}

	public RequestSpec validate() throws ClientSideValidationException {
		if (this.sent) throw new IllegalStateException("This request has already completed and can't be sent again.");
		List<Detail> details = new ArrayList<>();
		if (this.resource.resourceSpec.bodySerializer != null && this.content == null) details.add(new Detail(null, Failure.MISSING_BODY, null));
		if (resource.resourceSpec.parameters != null) {
			if (this.parameters == null) this.parameters = new HashMap<>();
			for (Entry<String, ParameterSpec> paramEntry : resource.resourceSpec.parameters.entrySet()) {
				ParameterSpec param = paramEntry.getValue();
				List<Object> values = this.parameters.get(param.name);
				if (param.required && param.defaultValue == null && (values == null || values.isEmpty())) details.add(new Detail(param, Failure.MISSING, null));
				if (param.type == ParameterType.PATH && values != null && values.size() > 1) details.add(new Detail(param, Failure.INVALID_SIZE, values.size()));
				if (param.regex != null && values != null && !values.isEmpty()) {
					for (Object value : values) {
						if (!Pattern.matches(param.regex, value.toString())) {
							details.add(new Detail(param, Failure.INVALID, value));
						}
					}
				}
			}
		}
		if (!details.isEmpty()) {
			throw new ClientSideValidationException(String.format("The validation for request \"%s\" failed with %d messages", resource.toString(), details.size()), details);
		}
		return this;
	}

	public RequestSpec send() throws ClientSideValidationException, ClientException {
		validate();
		response = resource.resourceSpec.client.engine.send(this);
		sent = true;
		return this;
	}

	public Response getResponse() {
		if (!this.sent) throw new IllegalStateException("The request was not sent yet.");
		checkResponseStatus();
		return response;
	}

	private void checkResponseStatus() {
		int status = this.response.getStatus();
		if (status >= 400) {
			switch (status) {
				case 400:
					break;

				default:
					break;
			}
		}
	}

	public <S, E> ResponseOptional<S, E> getDeserializedResponseBody(Class<S> successClass, Class<E> errorClass) {
		return new ResponseOptional<>(this, successClass, errorClass);
	}

	@SuppressWarnings("unchecked")
	public <T> T getDeserializedResponseBody(Class<T> returnType) throws IOException {
		Deserializer<?> deserializer = resource.resourceSpec.client.getDeserializer(returnType);
		if (deserializer == null) throw new IllegalArgumentException(String.format("No response body deserializer found for class %s", returnType.getName()));
		if (this.deserializedResponseBody == null && deserializer.getContentType().equalsIgnoreCase(response.getHeader(Header.CONTENT_TYPE).get(0))) {
			try (Response response = getResponse()) {
				this.deserializedResponseBody = deserializer.deserialize(response.getBody());
			}
		}
		return (T) this.deserializedResponseBody;
	}

}
