package com.github.leandrohsilveira.specfy.serialization.serializers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import com.github.leandrohsilveira.specfy.Serializer;
import com.github.leandrohsilveira.specfy.serialization.utils.GsonSerializationUtils;
import com.google.gson.GsonBuilder;

public class GsonSerializer implements Serializer {

	public GsonSerializer(GsonBuilder gsonBuilder) {
		this.gsonBuilder = gsonBuilder;
	}
	
	public GsonSerializer() {
		this(GsonSerializationUtils.getDefaultGsonBuilder());
	}
	
	private GsonBuilder gsonBuilder;

	@Override
	public void serialize(Object obj, OutputStream requestBody, Charset charset) throws IOException {
		try (OutputStreamWriter writer = new OutputStreamWriter(requestBody, charset)) {
			gsonBuilder.create().toJson(obj, writer);
			requestBody.flush();
		}
	}
}
