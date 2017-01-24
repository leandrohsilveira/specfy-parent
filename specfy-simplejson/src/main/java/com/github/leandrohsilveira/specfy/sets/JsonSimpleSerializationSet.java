package com.github.leandrohsilveira.specfy.sets;

import java.nio.charset.Charset;

import com.github.leandrohsilveira.specfy.RESTfulClientSpec;
import com.github.leandrohsilveira.specfy.SerializationSet;
import com.github.leandrohsilveira.specfy.deserializers.JSONArrayDeserializer;
import com.github.leandrohsilveira.specfy.deserializers.JSONObjectDeserializer;
import com.github.leandrohsilveira.specfy.serializers.JSONArraySerializer;
import com.github.leandrohsilveira.specfy.serializers.JSONObjectSerializer;

public class JsonSimpleSerializationSet implements SerializationSet {

	public void register(RESTfulClientSpec client) {
		Charset charset = client.getCharset();

		client.register(new JSONArraySerializer(charset));
		client.register(new JSONObjectSerializer(charset));

		client.register(new JSONArrayDeserializer(charset));
		client.register(new JSONObjectDeserializer(charset));

	}

}
