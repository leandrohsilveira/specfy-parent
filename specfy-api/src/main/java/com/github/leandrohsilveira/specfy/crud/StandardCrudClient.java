package com.github.leandrohsilveira.specfy.crud;

import com.github.leandrohsilveira.specfy.RESTfulClientSpec;
import com.github.leandrohsilveira.specfy.RequestSpec;
import com.github.leandrohsilveira.specfy.ResourceActionSpec;

public class StandardCrudClient {

	private RESTfulClientSpec client;
	private ResourceActionSpec resource;
	private ResourceActionSpec create;
	private ResourceActionSpec findAll;
	private ResourceActionSpec get;
	private ResourceActionSpec update;
	private ResourceActionSpec partialUpdate;
	private ResourceActionSpec delete;

	public StandardCrudClient(RESTfulClientSpec client, String resource, String keyParameterName, Class<?> entityClass) {
		this.client = client;

		this.resource = client.resource(resource).isAbstract();

		this.create = this.resource.subResource().isPOST(entityClass);
		this.findAll = this.resource.subResource().isGET();
		this.get = this.resource.subResource().pathParameter(keyParameterName, null).isGET();
		this.update = this.get.subResource().isPUT(entityClass);
		this.partialUpdate = this.get.subResource().isPATCH(entityClass);
		this.delete = this.get.subResource().isDELETE();
	}

	public RESTfulClientSpec getClient() {
		return client;
	}

	public RequestSpec create() {
		return this.create.newRequest();
	}

	public RequestSpec findAll() {
		return this.findAll.newRequest();
	}

	public RequestSpec get() {
		return this.get.newRequest();
	}

	public RequestSpec update() {
		return this.update.newRequest();
	}

	public RequestSpec partialUpdate() {
		return this.partialUpdate.newRequest();
	}

	public RequestSpec delete() {
		return this.delete.newRequest();
	}

}
