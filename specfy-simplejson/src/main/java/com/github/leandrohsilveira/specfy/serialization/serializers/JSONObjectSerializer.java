package com.github.leandrohsilveira.specfy.serialization.serializers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;

import com.github.leandrohsilveira.specfy.Serializer;

public class JSONObjectSerializer extends AbstracJsonSerializer implements Serializer {

	public void serialize(Object obj, OutputStream requestBody, Charset charset) throws IOException {
		((JSONObject) obj).writeJSONString(new OutputStreamWriter(requestBody, charset));
	}

	public Class<?> getSerializableClass() {
		return JSONObject.class;
	}

}
