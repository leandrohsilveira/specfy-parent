package com.github.leandrohsilveira.specfy;

import com.github.leandrohsilveira.specfy.exceptions.ClientException;
import com.github.leandrohsilveira.specfy.exceptions.ClientSideValidationException;

public interface Engine {

	public Response send(RequestSpec request) throws ClientSideValidationException, ClientException;

}
