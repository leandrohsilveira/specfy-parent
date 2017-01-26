package com.github.leandrohsilveira.specfy.serialization.serializers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.json.simple.JSONArray;

import com.github.leandrohsilveira.specfy.Serializer;

public class JSONArraySerializer implements Serializer {

	@Override
	public void serialize(Object obj, OutputStream requestBody, Charset charset) throws IOException {
		try (Writer writer = new OutputStreamWriter(requestBody, charset)) {
			((JSONArray) obj).writeJSONString(writer);
		}
	}

}
