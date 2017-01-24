package com.github.leandrohsilveira.specfy;

import com.github.leandrohsilveira.specfy.exceptions.ClientSpecException;
import com.github.leandrohsilveira.specfy.exceptions.ValidationException;

public interface Engine {

	public Response send(RequestSpec request) throws ValidationException, ClientSpecException;

}
