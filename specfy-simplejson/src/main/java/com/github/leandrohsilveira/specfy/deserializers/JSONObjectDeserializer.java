package com.github.leandrohsilveira.specfy.deserializers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONObjectDeserializer extends AbstracJsonDeserializer<JSONObject> {

	public JSONObjectDeserializer(Charset charset) {
		super(charset);
	}

	public Class<JSONObject> getSerializableClass() {
		return JSONObject.class;
	}

	public JSONObject deserialize(InputStream responseInput) throws IOException {
		try {
			return (JSONObject) new JSONParser().parse(new InputStreamReader(responseInput, charset));
		} catch (ParseException e) {
			throw new IOException(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(responseInput);
		}
	}

}
