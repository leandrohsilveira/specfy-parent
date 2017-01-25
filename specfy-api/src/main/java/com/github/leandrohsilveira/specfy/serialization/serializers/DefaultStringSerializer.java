package com.github.leandrohsilveira.specfy.serialization.serializers;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.github.leandrohsilveira.specfy.Serializer;

public class DefaultStringSerializer implements Serializer {

	@Override
	public Class<?> getSerializableClass() {
		return String.class;
	}

	@Override
	public String getContentType() {
		return "text/plain";
	}

	@Override
	public void serialize(Object obj, OutputStream requestBody, Charset charset) throws IOException {
		IOUtils.write(((String) obj), requestBody, charset);
	}

}
