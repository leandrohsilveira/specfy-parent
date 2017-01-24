package com.github.leandrohsilveira.specfy.serialization.deserializers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.github.leandrohsilveira.specfy.Deserializer;

public class DefaultStringDeserializer implements Deserializer<String> {

	private Charset charset;

	public DefaultStringDeserializer(Charset charset) {
		this.charset = charset;
	}

	@Override
	public Class<String> getSerializableClass() {
		return String.class;
	}

	@Override
	public String getContentType() {
		return "text/plain";
	}

	@Override
	public String deserialize(InputStream responseInput) throws IOException {
		return IOUtils.toString(responseInput, this.charset);
	}

}
