package com.github.leandrohsilveira.specfy;

import java.io.IOException;
import java.io.OutputStream;

public interface Serializer {

	Class<?> getSerializableClass();

	String getContentType();

	void serialize(Object obj, OutputStream requestBody) throws IOException;

}
