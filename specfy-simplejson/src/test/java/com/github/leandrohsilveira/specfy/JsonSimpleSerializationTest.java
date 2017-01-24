package com.github.leandrohsilveira.specfy;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import com.github.leandrohsilveira.specfy.exceptions.ClientException;
import com.github.leandrohsilveira.specfy.exceptions.ClientSideValidationException;
import com.github.leandrohsilveira.specfy.serializers.JSONObjectSerializer;
import com.github.leandrohsilveira.specfy.sets.JsonSimpleSerializationSet;

public class JsonSimpleSerializationTest {

	/**
	 * Creating an API root with an Serialization Set defined. It apply for serialization and
	 * deserialization of the {@link JSONObject} and {@link JSONArray} objects.
	 * 
	 * The isPOST method requires an class to specify what kind of object will be writen in request
	 * body. The {@link JSONObjectSerializer} defined in {@link JsonSimpleSerializationSet} will set
	 * the request content type to application/json.
	 */
	private static RESTfulClientSpec root = Spec.http().host("localhost").port(8080).api("api").register(new JsonSimpleSerializationSet());

	private static ResourceActionSpec createUser = root.resource("users").isPOST(JSONObject.class);

	@Test
	public void hasSerializerTest() {
		
		createUser.newRequest()
		
	}
}
