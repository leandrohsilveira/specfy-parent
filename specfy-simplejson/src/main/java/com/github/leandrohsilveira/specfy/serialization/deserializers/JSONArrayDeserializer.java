package com.github.leandrohsilveira.specfy.serialization.deserializers;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONArrayDeserializer extends AbstracJsonDeserializer {

	@Override
	public Class<JSONArray> getSerializableClass() {
		return JSONArray.class;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T deserialize(InputStream responseInput, Class<T> returnType, Charset charset) throws IOException {
		try {
			return (T) new JSONParser().parse(new InputStreamReader(responseInput, charset));
		} catch (ParseException e) {
			throw new IOException(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(responseInput);
		}
	}

}
