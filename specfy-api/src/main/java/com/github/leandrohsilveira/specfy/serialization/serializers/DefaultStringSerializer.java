package com.github.leandrohsilveira.specfy.serialization.serializers;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.github.leandrohsilveira.specfy.Serializer;

public class DefaultStringSerializer implements Serializer {

	private Charset charset;

	public DefaultStringSerializer(Charset charset) {
		this.charset = charset;
	}

	@Override
	public Class<?> getSerializableClass() {
		return String.class;
	}

	@Override
	public String getContentType() {
		return "text/plain";
	}

	@Override
	public void serialize(Object obj, OutputStream requestBody) throws IOException {
		IOUtils.write(((String) obj), requestBody, this.charset);
	}

}
