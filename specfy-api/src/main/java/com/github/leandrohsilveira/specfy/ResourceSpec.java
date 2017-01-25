package com.github.leandrohsilveira.specfy;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.github.leandrohsilveira.specfy.Spec.Composer;
import com.github.leandrohsilveira.specfy.utils.SpecfyUtils;

public class ResourceSpec extends Composer {

	protected RESTfulClientSpec client;
	protected String resource;
	protected Map<String, ParameterSpec> parameters;
	protected String contentType;

	protected ResourceSpec(RESTfulClientSpec client, String resource) {
		this.client = client;
		this.resource = resource;
	}

	public ResourceActionSpec isAbstract() {
		return new ResourceActionSpec(this, null);
	}

	public ResourceActionSpec isGET() {
		return new ResourceActionSpec(this, RequestMethod.GET);
	}

	public ResourceActionSpec isHEAD() {
		return new ResourceActionSpec(this, RequestMethod.HEAD);
	}

	public ResourceActionSpec isOPTIONS() {
		return new ResourceActionSpec(this, RequestMethod.OPTIONS);
	}

	public ResourceActionSpec isPOST(String contentType) {
		this.contentType = contentType;
		return new ResourceActionSpec(this, RequestMethod.POST);
	}

	public ResourceActionSpec isPUT(String contentType) {
		this.contentType = contentType;
		return new ResourceActionSpec(this, RequestMethod.PUT);
	}

	public ResourceActionSpec isPATCH(String contentType) {
		this.contentType = contentType;
		return new ResourceActionSpec(this, RequestMethod.PATCH);
	}

	public ResourceActionSpec isDELETE() {
		return new ResourceActionSpec(this, RequestMethod.DELETE);
	}

	public Serializer getSerializer() {
		if (contentType != null)
			return client.getSerializer(contentType);
		return null;
	}

	private ResourceSpec attribute(ParameterSpec parameterSpec) {
		if (parameterSpec == null) throw new NullPointerException("No parameter spec provided");
		if (parameters == null) parameters = new HashMap<>();
		if (parameters.containsKey(parameterSpec.name)) throw new IllegalArgumentException(String.format("Parameter \"%s\" already defined in spec, choose another name.", parameterSpec.name));
		parameters.put(parameterSpec.name, parameterSpec);
		return this;
	}

	public ResourceSpec pathParameter(String parameterName, String regex) {
		attribute(new ParameterSpec(ParameterType.PATH, parameterName, regex, true));
		this.resource = SpecfyUtils.safeConcat(this.resource, String.format("{%s}", parameterName));
		return this;
	}

	public ResourceSpec header(String headerName, String regex, boolean required) {
		return attribute(new ParameterSpec(ParameterType.HEADER, headerName, regex, required));
	}

	public ResourceSpec header(Header header, String regex, boolean required) {
		return attribute(new ParameterSpec(ParameterType.HEADER, header.getHeaderName(), regex, required));
	}

	public ResourceSpec header(String headerName, String regex, boolean required, Object defaultValue) {
		return attribute(new ParameterSpec(ParameterType.HEADER, headerName, regex, required, defaultValue));
	}

	public ResourceSpec header(Header header, String regex, boolean required, Object defaultValue) {
		return attribute(new ParameterSpec(ParameterType.HEADER, header.getHeaderName(), regex, required, defaultValue));
	}

	public ResourceSpec queryParameter(String parameterName, String regex) {
		return attribute(new ParameterSpec(ParameterType.QUERY, parameterName, regex, false));
	}

	public ResourceSpec queryParameter(String parameterName, String regex, Object defaultValue) {
		return attribute(new ParameterSpec(ParameterType.QUERY, parameterName, regex, false, defaultValue));
	}

	public ResourceSpec cookie(String cookieName, String regex, boolean required) {
		return attribute(new ParameterSpec(ParameterType.COOKIE, cookieName, regex, required));
	}

	public ResourceSpec cookie(String cookieName, String regex, boolean required, Object defaultValue) {
		return attribute(new ParameterSpec(ParameterType.COOKIE, cookieName, regex, required, defaultValue));
	}

	public ResourceSpec fixedHeader(Header header, Object value) {
		return attribute(new ParameterSpec(ParameterType.HEADER, header.getHeaderName(), value));
	}

	public ResourceSpec fixedHeader(String headerName, Object value) {
		return attribute(new ParameterSpec(ParameterType.HEADER, headerName, value));
	}

	public ResourceSpec fixedCookie(String cookieName, Object value) {
		return attribute(new ParameterSpec(ParameterType.COOKIE, cookieName, value));
	}

	public ResourceSpec fixedQueryParameter(String parameterName, Object value) {
		return attribute(new ParameterSpec(ParameterType.QUERY, parameterName, value));
	}

	public ResourceSpec inherits(ResourceActionSpec resourceActionToInherit, String... parametersNamesToInherit) {
		for (String parameterName : parametersNamesToInherit) {
			attribute(resourceActionToInherit.resourceSpec.parameters.get(parameterName));
		}
		return this;
	}

	public ResourceSpec inheritsAll(ResourceActionSpec resourceActionToInherit) {
		for (Entry<String, ParameterSpec> entry : resourceActionToInherit.resourceSpec.parameters.entrySet()) {
			if (entry.getValue().type != ParameterType.PATH)
				attribute(entry.getValue());
		}
		return this;
	}

	@Override
	protected String compose() {
		return SpecfyUtils.safeConcat(client.resourcesRoot, resource);
	}

	public boolean isBodyRequired() {
		return contentType != null;
	}

}
