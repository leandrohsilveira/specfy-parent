package com.github.leandrohsilveira.specfy;

import java.util.HashMap;
import java.util.Map.Entry;

import com.github.leandrohsilveira.specfy.utils.SpecfyUtils;

public class ResourceActionSpec {

	public ResourceActionSpec(ResourceSpec resourceSpec, RequestMethod method) {
		this.resourceSpec = resourceSpec;
		this.method = method;
		this._abstract = method == null;
	}

	protected ResourceSpec resourceSpec;
	protected RequestMethod method;
	protected boolean _abstract;

	@Override
	public String toString() {
		return String.format("%s /%s", method != null ? method : "ABSTRACT", resourceSpec.compose());
	}

	public ResourceSpec subResource() {
		ResourceSpec subResourceSpec = new ResourceSpec(resourceSpec.client, this.resourceSpec.resource);
		copyParameters(resourceSpec, subResourceSpec);
		return subResourceSpec;
	}

	public ResourceSpec subResource(String resource) {
		ResourceSpec subResourceSpec = new ResourceSpec(resourceSpec.client, SpecfyUtils.safeConcat(this.resourceSpec.resource, resource));
		copyParameters(resourceSpec, subResourceSpec);
		return subResourceSpec;
	}

	public RequestSpec newLocalRequest() {
		return newRequest(resourceSpec.client.host);
	}

	public RequestSpec newRequest(String host) {
		if (this._abstract) throw new IllegalStateException(String.format("The resource %s is abstract and can't send requests.", toString()));
		return new RequestSpec(this, host);
	}

	private void copyParameters(ResourceSpec from, ResourceSpec to) {
		if (from.parameters != null) for (Entry<String, ParameterSpec> paramEntry : from.parameters.entrySet()) {
			if (to.parameters == null) to.parameters = new HashMap<>();
			if (paramEntry.getValue().type == ParameterType.PATH) to.parameters.put(paramEntry.getKey(), paramEntry.getValue());
		}
	}

}
