package com.github.leandrohsilveira.specfy.serialization.serializers;

import java.nio.charset.Charset;

import com.github.leandrohsilveira.specfy.RESTfulClientSpec;
import com.github.leandrohsilveira.specfy.SerializationSet;
import com.github.leandrohsilveira.specfy.serialization.deserializers.DefaultStringDeserializer;

public class DefaultStringSerializationSet implements SerializationSet {

	@Override
	public void register(RESTfulClientSpec client) {
		Charset charset = client.getCharset();

		client.register(new DefaultStringSerializer(charset));
		client.register(new DefaultStringDeserializer(charset));
	}

}
