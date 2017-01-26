package com.github.leandrohsilveira.specfy;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public interface Serializer {

	void serialize(Object obj, OutputStream requestBody, Charset charset) throws IOException;

}
