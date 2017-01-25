package com.github.leandrohsilveira.specfy.serialization.sets;

import com.github.leandrohsilveira.specfy.RESTfulClientSpec;
import com.github.leandrohsilveira.specfy.SerializationSet;
import com.github.leandrohsilveira.specfy.serialization.deserializers.JSONArrayDeserializer;
import com.github.leandrohsilveira.specfy.serialization.deserializers.JSONObjectDeserializer;
import com.github.leandrohsilveira.specfy.serialization.serializers.JSONArraySerializer;
import com.github.leandrohsilveira.specfy.serialization.serializers.JSONObjectSerializer;

public class JsonSimpleSerializationSet implements SerializationSet {

	public void register(RESTfulClientSpec client) {
		client.register(new JSONArraySerializer());
		client.register(new JSONObjectSerializer());

		client.register(new JSONArrayDeserializer());
		client.register(new JSONObjectDeserializer());

	}

}
