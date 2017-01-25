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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.leandrohsilveira.specfy.exceptions.ResponseException;
import com.github.leandrohsilveira.specfy.exceptions.ValidationException;
import com.github.leandrohsilveira.specfy.exceptions.ValidationException.Detail;
import com.github.leandrohsilveira.specfy.exceptions.ValidationException.Detail.Failure;
import com.github.leandrohsilveira.specfy.exceptions.http.ClientError;
import com.github.leandrohsilveira.specfy.exceptions.http.ServerError;

public class RequestSpec {

	private static final Logger LOG = LoggerFactory.getLogger(RequestSpec.class);

	protected RequestSpec(ResourceActionSpec resourceActionSpec, String host) {
		super();
		this.resourceActionSpec = resourceActionSpec;
		this.host = host;
	}

	protected String host;

	protected ResourceActionSpec resourceActionSpec;

	protected Map<String, List<Object>> parameters;

	protected Object content;

	protected SSLContext sslContext;

	protected boolean sent = false;

	private Response response;

	private Object deserializedResponseBody;

	protected Serializer serializer;

	public RequestSpec useSsl(SSLContext sslContext) {
		this.sslContext = sslContext;
		return this;
	}

	public RequestSpec bind(String parameterName, Object parameterValue) {
		LOG.debug("Binding {} to parameter {}", parameterValue, parameterName);
		if (this.resourceActionSpec.resourceSpec.parameters == null || this.resourceActionSpec.resourceSpec.parameters.isEmpty())
			throw new IllegalArgumentException(String.format("There is no parameter in client specification.", parameterName));
		ParameterSpec parameterSpec = this.resourceActionSpec.resourceSpec.parameters.get(parameterName);
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
		LOG.debug("Setting body content {}");
		if (content != null) {
			serializer = this.resourceActionSpec.resourceSpec.client.getSerializer(content.getClass());
			if (serializer == null) serializer = this.resourceActionSpec.resourceSpec.getSerializer();
			if (serializer == null) throw new IllegalArgumentException(String.format("No suitable serializer found for class %s or content-type %s", content.getClass().getName(), resourceActionSpec.resourceSpec.contentType));
			this.content = content;
		}
		return this;
	}

	public RequestSpec validate() throws ValidationException {
		if (this.sent) throw new IllegalStateException("This request has already completed and can't be sent again.");
		LOG.debug("Stating Request validation");
		List<Detail> details = new ArrayList<>();
		if (this.resourceActionSpec.resourceSpec.isBodyRequired() && this.content == null) details.add(new Detail(null, Failure.MISSING_BODY, null));
		if (resourceActionSpec.resourceSpec.parameters != null) {
			if (this.parameters == null) this.parameters = new HashMap<>();
			for (Entry<String, ParameterSpec> paramEntry : resourceActionSpec.resourceSpec.parameters.entrySet()) {
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
			LOG.debug("Validation failed: {}.", details);
			throw new ValidationException(String.format("The validation for request \"%s\" failed with %d messages", resourceActionSpec.toString(), details.size()), details);
		}
		LOG.debug("Validation complete.");
		return this;
	}

	public RequestSpec send() throws ValidationException, ClientError, ServerError, IOException {
		validate();
		response = resourceActionSpec.resourceSpec.client.engine.send(this);
		sent = true;
		ResponseException.checkResponseStatus(this);
		return this;
	}

	public Response getResponse() {
		if (!this.sent) throw new IllegalStateException("The request was not sent yet.");
		return response;
	}

	@SuppressWarnings("unchecked")
	public <T> T getEntity(Class<T> returnType) throws IOException {
		String contentType = response.getHeader(Header.CONTENT_TYPE, 0);
		Deserializer deserializer = resourceActionSpec.resourceSpec.client.getDeserializer(returnType);
		if (deserializer == null && contentType != null) {
			deserializer = resourceActionSpec.resourceSpec.client.getDeserializer(contentType);
		}
		if (deserializer == null) throw new IllegalArgumentException(String.format("No response body deserializer found for class %s", returnType.getName()));
		if (this.deserializedResponseBody == null && deserializer.getContentType().equalsIgnoreCase(response.getHeader(Header.CONTENT_TYPE, 0))) {
			try (Response response = getResponse()) {
				this.deserializedResponseBody = deserializer.deserialize(response.getBody(), returnType, this.resourceActionSpec.resourceSpec.client.charset);
			}
		}
		return (T) this.deserializedResponseBody;
	}

}
