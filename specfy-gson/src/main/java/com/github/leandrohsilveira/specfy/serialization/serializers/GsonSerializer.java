package com.github.leandrohsilveira.specfy.serialization.serializers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import com.github.leandrohsilveira.specfy.Serializer;
import com.github.leandrohsilveira.specfy.serialization.utils.GsonSerializationUtils;

public class GsonSerializer implements Serializer {

	@Override
	public void serialize(Object obj, OutputStream requestBody, Charset charset) throws IOException {
		try (OutputStreamWriter writer = new OutputStreamWriter(requestBody, charset)) {
			GsonSerializationUtils.GSON.toJson(obj, writer);
			requestBody.flush();
		}
	}
}
