package com.github.leandrohsilveira.specfy.serialization.deserializers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.github.leandrohsilveira.specfy.Deserializer;

public class StringDeserializer implements Deserializer {

	@Override
	@SuppressWarnings("unchecked")
	public <T> T deserialize(InputStream responseInput, Class<T> returnType, Charset charset) throws IOException {
		return (T) IOUtils.toString(responseInput, charset);
	}

}
