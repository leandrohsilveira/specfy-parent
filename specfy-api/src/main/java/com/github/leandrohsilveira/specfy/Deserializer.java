package com.github.leandrohsilveira.specfy;

import java.io.IOException;
import java.io.InputStream;

public interface Deserializer<T> {

	Class<T> getSerializableClass();

	String getContentType();

	T deserialize(InputStream responseInput) throws IOException;

}
