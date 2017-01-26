package com.github.leandrohsilveira.specfy.serialization.serializers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;

import com.github.leandrohsilveira.specfy.Serializer;

public class JSONObjectSerializer implements Serializer {

	@Override
	public void serialize(Object obj, OutputStream requestBody, Charset charset) throws IOException {
		try (Writer writer = new OutputStreamWriter(requestBody, charset)) {
			((JSONObject) obj).writeJSONString(writer);
		}
	}

}
