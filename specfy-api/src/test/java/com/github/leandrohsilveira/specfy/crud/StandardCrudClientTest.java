package com.github.leandrohsilveira.specfy.crud;

import com.github.leandrohsilveira.specfy.RESTfulClientSpec;
import com.github.leandrohsilveira.specfy.Spec;
import com.github.leandrohsilveira.specfy.serialization.WwwFormUrlEncoded;

public class StandardCrudClientTest {

	private RESTfulClientSpec root = Spec.http().host("localhost").port(8080).api("api");
	private StandardCrudClient userResources = new StandardCrudClient(root, "users", "userId", WwwFormUrlEncoded.class);

}
