package com.github.leandrohsilveira.specfy.serializers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.json.simple.JSONArray;

import com.github.leandrohsilveira.specfy.Serializer;

public class JSONArraySerializer extends AbstracJsonSerializer implements Serializer {

	public JSONArraySerializer(Charset charset) {
		super(charset);
	}

	public Class<?> getSerializableClass() {
		return JSONArray.class;
	}

	public void serialize(Object obj, OutputStream requestBody) throws IOException {
		((JSONArray) obj).writeJSONString(new OutputStreamWriter(requestBody, charset));
	}

}
