package com.github.leandrohsilveira.specfy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.github.leandrohsilveira.specfy.exceptions.ValidationException;
import com.github.leandrohsilveira.specfy.serialization.WwwFormUrlEncoded;

public class ResourceSpecTest {

	private static final String NUMBER_REGEX = "^\\d+$";
	private RESTfulClientSpec root = new RESTfulClientSpec("/api");

	@Test
	public void teste() {
		String entry = "/replace/last/";
		String replaced = entry.replaceAll("\\/$", "");
		assertEquals("/replace/last", replaced);
	}

	@Test
	public void simpleResourceTest() {
		ResourceActionSpec findAllUsers = root.resource("users").isGET();
		assertEquals("GET /api/users", findAllUsers.toString());
	}

	@Test
	public void simpleSubResourceTest() {
		ResourceActionSpec findAllUsers = root.resource("users").isGET();
		assertEquals("GET /api/users", findAllUsers.toString());

		ResourceActionSpec createUser = findAllUsers.subResource().isPOST();
		assertEquals("POST /api/users", createUser.toString());

		ResourceActionSpec getUser = findAllUsers.subResource().pathParameter("userId", NUMBER_REGEX).isGET();
		assertEquals("GET /api/users/{userId}", getUser.toString());
	}

	@Test
	public void multiLevelSubResourcesTest() {
		ResourceActionSpec findAllUsers = root.resource("users").isGET();
		assertEquals("GET /api/users", findAllUsers.toString());

		ResourceActionSpec getUser = findAllUsers.subResource().pathParameter("userId", NUMBER_REGEX).isGET();
		assertEquals("GET /api/users/{userId}", getUser.toString());

		ResourceActionSpec findUserGroups = getUser.subResource("groups").isGET();
		assertEquals("GET /api/users/{userId}/groups", findUserGroups.toString());

		ResourceActionSpec getUserGroup = findUserGroups.subResource().pathParameter("groupId", NUMBER_REGEX).isGET();
		assertEquals("GET /api/users/{userId}/groups/{groupId}", getUserGroup.toString());
	}

	@Test(expected = IllegalArgumentException.class)
	public void bindToUndefinedParameterTest() {
		ResourceActionSpec findAllUsers = root.resource("users").isGET();
		assertEquals("GET /api/users", findAllUsers.toString());
		findAllUsers.newLocalRequest().bind("page", 1);
		fail("The bind action should fail if there's no 'page' parameter defined.");
	}

	@Test
	public void bindParameterTest() {
		ResourceActionSpec getUsers = root.resource("users").pathParameter("userId", NUMBER_REGEX).isGET();
		assertEquals("GET /api/users/{userId}", getUsers.toString());
		getUsers.newLocalRequest().bind("userId", 1);
	}

	@Test
	public void abstractResourceTest() {

		// Creating an abstract resource to define common parameters:
		// Query parameters are optional by default.
		ResourceActionSpec abstractFindResource = root.resource().queryParameter("page", NUMBER_REGEX).queryParameter("max", "^(10|20|30)$").queryParameter("query", null).isAbstract();

		// Creating a new resource that inherits only the "page" parameter from abstractFindResource
		ResourceActionSpec simpleFindAllUsers = root.resource("users").inherits(abstractFindResource, "page").isGET();
		assertEquals("GET /api/users", simpleFindAllUsers.toString());

		// Now the bind is avalilable by inheritance.
		simpleFindAllUsers.newLocalRequest().bind("page", 1);

		// Also, a resource can inherits all parameters from abstractFindResource by convenience
		ResourceActionSpec completeFindAllUsers = root.resource("users").inheritsAll(abstractFindResource).isGET();

		// Now all binds should work.
		completeFindAllUsers.newLocalRequest().bind("page", 1).bind("max", 10).bind("query", "I'm work");
	}

	@Test(expected = ValidationException.class)
	public void pathParameterNotBoundTest() throws Exception {

		// Path parameters are required by default
		ResourceActionSpec getUser = root.resource("users").pathParameter("userId", NUMBER_REGEX).isGET();
		assertEquals("GET /api/users/{userId}", getUser.toString());

		getUser.newLocalRequest().validate();

	}

	@Test(expected = ValidationException.class)
	public void pathParameterBoundTwiceTest() throws Exception {

		ResourceActionSpec getUser = root.resource("users").pathParameter("userId", NUMBER_REGEX).isGET();
		assertEquals("GET /api/users/{userId}", getUser.toString());

		// Path parameters can't be bound twice!
		getUser.newLocalRequest().bind("userId", 1).bind("userId", 2).validate();

	}

	@Test(expected = ValidationException.class)
	public void requiredParameterNotBoundTest() throws Exception {

		// Creating a resource where the Authorization Header is required
		ResourceActionSpec getUser = root.resource("users").header(Header.AUTHORIZATION, null, true).isGET();
		assertEquals("GET /api/users", getUser.toString());

		getUser.newLocalRequest().validate();

	}

	@Test
	public void defaultParameterValueTest() throws Exception {

		// Creating a resource where the Accept Header is required and a default value is defined.
		// The default value will be overriden if a new value is bound.
		ResourceActionSpec getUser = root.resource("users").header(Header.ACCEPT, null, true, "application/json").isGET();
		assertEquals("GET /api/users", getUser.toString());

		getUser.newLocalRequest().validate();

	}

	@Test
	public void fixedParameterValueTest() throws Exception {

		// Creating a resource where the Accept Header has an fixed value. The fixed value will
		// remain even if a new value is bound.
		ResourceActionSpec getUser = root.resource("users").fixedHeader(Header.ACCEPT, "application/json").isGET();
		assertEquals("GET /api/users", getUser.toString());

		getUser.newLocalRequest().validate();

	}

	@Test
	public void requestBodyTest() throws Exception {
		ResourceActionSpec createUser = root.resource("users").isPOST();
		createUser.newLocalRequest().build().serialize(new WwwFormUrlEncoded().bind("username", "test").bind("password", "test123"));
	}

}
