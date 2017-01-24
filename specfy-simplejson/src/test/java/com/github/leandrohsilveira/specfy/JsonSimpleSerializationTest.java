package com.github.leandrohsilveira.specfy;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import com.github.leandrohsilveira.specfy.exceptions.ClientSideValidationException;
import com.github.leandrohsilveira.specfy.sets.JsonSimpleSerializationSet;

public class JsonSimpleSerializationTest {

	/**
	 * Creating an API root with an Serialization Set defined. It apply for serialization and
	 * deserialization of the {@link JSONObject} and {@link JSONArray} objects.
	 */
	private static RESTfulClientSpec root = Spec.http().host("localhost").port(8080).api("api").register(new JsonSimpleSerializationSet());

	@Test
	@SuppressWarnings("unchecked")
	public void jsonSimpleBodyTest() throws Exception {
		ResourceActionSpec createUser = root.resource("users").isPOST(JSONObject.class);
		JSONObject user = new JSONObject();
		user.put("username", "test");
		user.put("password", "test123");
		createUser.newRequest().body(user).validate();
	}

	@Test(expected = ClientSideValidationException.class)
	@SuppressWarnings("unchecked")
	public void jsonSimpleRequiredBodyNotBoundTest() throws Exception {
		ResourceActionSpec createUser = root.resource("users").isPOST(JSONObject.class);
		createUser.newRequest().validate();
	}
}
