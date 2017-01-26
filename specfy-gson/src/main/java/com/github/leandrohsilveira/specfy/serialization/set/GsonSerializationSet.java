package com.github.leandrohsilveira.specfy.serialization.set;

import com.github.leandrohsilveira.specfy.RESTfulClientSpec;
import com.github.leandrohsilveira.specfy.SerializationSet;
import com.github.leandrohsilveira.specfy.serialization.deserializers.GsonDeserializer;
import com.github.leandrohsilveira.specfy.serialization.serializers.GsonSerializer;

public class GsonSerializationSet implements SerializationSet {

	@Override
	public void register(RESTfulClientSpec client) {
		client.register(new GsonSerializer());
		client.register(new GsonDeserializer());
	}

}
