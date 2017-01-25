package com.github.leandrohsilveira.specfy;

import java.io.IOException;

public interface Engine {

	public Response send(RequestSpec request) throws IOException;

}
