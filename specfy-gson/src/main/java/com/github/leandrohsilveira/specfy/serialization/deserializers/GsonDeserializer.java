package com.github.leandrohsilveira.specfy.serialization.deserializers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.github.leandrohsilveira.specfy.Deserializer;
import com.github.leandrohsilveira.specfy.serialization.utils.GsonSerializationUtils;
import com.google.gson.GsonBuilder;

public class GsonDeserializer implements Deserializer {

	public GsonDeserializer(GsonBuilder gsonBuilder) {
		this.gsonBuilder = gsonBuilder;
	}
	
	private GsonBuilder gsonBuilder;

	@Override
	public <T> T deserialize(InputStream responseInput, Class<T> returnType, Charset charset) throws IOException {
		try (InputStream input = responseInput;
				InputStreamReader reader = new InputStreamReader(input, charset);) {
			return gsonBuilder.create().fromJson(reader, returnType);
		}
	}

}
