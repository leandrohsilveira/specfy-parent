package com.github.leandrohsilveira.specfy.deserializers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONArrayDeserializer extends AbstracJsonDeserializer<JSONArray> {

	public JSONArrayDeserializer(Charset charset) {
		super(charset);
	}

	public Class<JSONArray> getSerializableClass() {
		return JSONArray.class;
	}

	public JSONArray deserialize(InputStream responseInput) throws IOException {
		try {
			return (JSONArray) new JSONParser().parse(new InputStreamReader(responseInput, charset));
		} catch (ParseException e) {
			throw new IOException(e.getMessage(), e);
		}
	}

}
