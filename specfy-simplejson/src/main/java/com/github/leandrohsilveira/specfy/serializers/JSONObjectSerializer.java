package com.github.leandrohsilveira.specfy.serializers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;

import com.github.leandrohsilveira.specfy.Serializer;

public class JSONObjectSerializer extends AbstracJsonSerializer implements Serializer {

	public JSONObjectSerializer(Charset charset) {
		super(charset);
	}

	public void serialize(Object obj, OutputStream requestBody) throws IOException {
		((JSONObject) obj).writeJSONString(new OutputStreamWriter(requestBody, charset));
	}

	public Class<?> getSerializableClass() {
		return JSONObject.class;
	}

}
