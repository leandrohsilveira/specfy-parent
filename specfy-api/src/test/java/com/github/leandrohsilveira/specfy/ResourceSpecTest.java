package com.github.leandrohsilveira.specfy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.leandrohsilveira.specfy.exceptions.ValidationException;
import com.github.leandrohsilveira.specfy.serialization.WwwFormUrlEncoded;

public class ResourceSpecTest {

	private static final String NUMBER_REGEX = "^\\d+$";
	private RESTfulClientSpec root = Spec.http().host("localhost").port(8080).api("api");

	private static final Logger log = LoggerFactory.getLogger(ResourceSpecTest.class);

	@Test
	public void simpleResourceTest() {
		ResourceActionSpec findAllUsers = root.resource("users").isGET();
		assertEquals("GET http://localhost:8080/api/users", findAllUsers.toString());
	}

	@Test
	public void simpleSubResourceTest() {
		ResourceActionSpec findAllUsers = root.resource("users").isGET();
		assertEquals("GET http://localhost:8080/api/users", findAllUsers.toString());

		ResourceActionSpec createUser = findAllUsers.subResource().isPOST(WwwFormUrlEncoded.class);
		assertEquals("POST http://localhost:8080/api/users", createUser.toString());

		ResourceActionSpec getUser = findAllUsers.subResource().pathParameter("userId", NUMBER_REGEX).isGET();
		assertEquals("GET http://localhost:8080/api/users/{userId}", getUser.toString());
	}

	@Test
	public void multiLevelSubResourcesTest() {
		ResourceActionSpec findAllUsers = root.resource("users").isGET();
		assertEquals("GET http://localhost:8080/api/users", findAllUsers.toString());

		ResourceActionSpec getUser = findAllUsers.subResource().pathParameter("userId", NUMBER_REGEX).isGET();
		assertEquals("GET http://localhost:8080/api/users/{userId}", getUser.toString());

		ResourceActionSpec findUserGroups = getUser.subResource("groups").isGET();
		assertEquals("GET http://localhost:8080/api/users/{userId}/groups", findUserGroups.toString());

		ResourceActionSpec getUserGroup = findUserGroups.subResource().pathParameter("groupId", NUMBER_REGEX).isGET();
		assertEquals("GET http://localhost:8080/api/users/{userId}/groups/{groupId}", getUserGroup.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void bindToUndefinedParameterTest() {
		ResourceActionSpec findAllUsers = root.resource("users").isGET();
		assertEquals("GET http://localhost:8080/api/users", findAllUsers.toString());
		findAllUsers.newRequest().bind("page", 1);
		fail("The bind action should fail if there's no 'page' parameter defined.");
	}

	@Test
	public void bindParameterTest() {
		ResourceActionSpec getUsers = root.resource("users").pathParameter("userId", NUMBER_REGEX).isGET();
		assertEquals("GET http://localhost:8080/api/users/{userId}", getUsers.toString());
		getUsers.newRequest().bind("userId", 1);
	}

	@Test
	public void abstractResourceTest() {

		// Creating an abstract resource to define common parameters:
		// Query parameters are optional by default.
		ResourceActionSpec abstractFindResource = root.resource().queryParameter("page", NUMBER_REGEX).queryParameter("max", "^(10|20|30)$").queryParameter("query", null).isAbstract();

		// Creating a new resource that inherits only the "page" parameter from abstractFindResource
		ResourceActionSpec simpleFindAllUsers = root.resource("users").inherits(abstractFindResource, "page").isGET();
		assertEquals("GET http://localhost:8080/api/users", simpleFindAllUsers.toString());

		// Now the bind is avalilable by inheritance.
		simpleFindAllUsers.newRequest().bind("page", 1);

		// Also, a resource can inherits all parameters from abstractFindResource by convenience
		ResourceActionSpec completeFindAllUsers = root.resource("users").inheritsAll(abstractFindResource).isGET();

		// Now all binds should work.
		completeFindAllUsers.newRequest().bind("page", 1).bind("max", 10).bind("query", "I'm work");
	}

	@Test(expected = ValidationException.class)
	public void pathParameterNotBoundTest() throws Exception {

		// Path parameters are required by default
		ResourceActionSpec getUser = root.resource("users").pathParameter("userId", NUMBER_REGEX).isGET();
		assertEquals("GET http://localhost:8080/api/users/{userId}", getUser.toString());

		getUser.newRequest().validate();

	}

	@Test(expected = ValidationException.class)
	public void pathParameterBoundTwiceTest() throws Exception {

		ResourceActionSpec getUser = root.resource("users").pathParameter("userId", NUMBER_REGEX).isGET();
		assertEquals("GET http://localhost:8080/api/users/{userId}", getUser.toString());

		// Path parameters can't be bound twice!
		getUser.newRequest().bind("userId", 1).bind("userId", 2).validate();

	}

	@Test(expected = ValidationException.class)
	public void requiredParameterNotBoundTest() throws Exception {

		// Creating a resource where the Authorization Header is required
		ResourceActionSpec getUser = root.resource("users").header(Header.AUTHORIZATION, null, true).isGET();
		assertEquals("GET http://localhost:8080/api/users", getUser.toString());

		getUser.newRequest().validate();

	}

	@Test
	public void defaultParameterValueTest() throws Exception {

		// Creating a resource where the Accept Header is required and a default value is defined.
		// The default value will be overriden if a new value is bound.
		ResourceActionSpec getUser = root.resource("users").header(Header.ACCEPT, null, true, "application/json").isGET();
		assertEquals("GET http://localhost:8080/api/users", getUser.toString());

		getUser.newRequest().validate();

	}

	@Test
	public void fixedParameterValueTest() throws Exception {

		// Creating a resource where the Accept Header has an fixed value. The fixed value will
		// remain even if a new value is bound.
		ResourceActionSpec getUser = root.resource("users").fixedHeader(Header.ACCEPT, "application/json").isGET();
		assertEquals("GET http://localhost:8080/api/users", getUser.toString());

		getUser.newRequest().validate();

	}

	@Test
	public void requestBodyTest() throws Exception {
		ResourceActionSpec createUser = root.resource("users").isPOST(WwwFormUrlEncoded.class);
		createUser.newRequest().body(new WwwFormUrlEncoded().bind("username", "test").bind("password", "test123")).validate();
	}

	@Test(expected = ValidationException.class)
	public void requiredRequestBodyNotBoundTest() throws Exception {
		ResourceActionSpec createUser = root.resource("users").isPOST(WwwFormUrlEncoded.class);
		createUser.newRequest().validate();
	}

}
