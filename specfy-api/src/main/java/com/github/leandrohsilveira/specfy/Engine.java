package com.github.leandrohsilveira.specfy;

import java.io.IOException;

public interface Engine {

	Request prepareRequest(RequestSpec requestSpec) throws IOException;

}
