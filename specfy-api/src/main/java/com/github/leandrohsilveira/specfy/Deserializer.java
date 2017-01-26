package com.github.leandrohsilveira.specfy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public interface Deserializer {

	<T> T deserialize(InputStream responseInput, Class<T> returnType, Charset charset) throws IOException;

}
