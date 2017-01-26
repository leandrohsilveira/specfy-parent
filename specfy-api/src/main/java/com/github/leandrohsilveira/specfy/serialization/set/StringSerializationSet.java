package com.github.leandrohsilveira.specfy.serialization.set;

import com.github.leandrohsilveira.specfy.RESTfulClientSpec;
import com.github.leandrohsilveira.specfy.SerializationSet;
import com.github.leandrohsilveira.specfy.serialization.deserializers.StringDeserializer;
import com.github.leandrohsilveira.specfy.serialization.serializers.StringSerializer;

public class StringSerializationSet implements SerializationSet {

	@Override
	public void register(RESTfulClientSpec client) {
		StringSerializer serializer = new StringSerializer();
		StringDeserializer deserializer = new StringDeserializer();

		client.register(String.class, serializer);
		client.register(String.class, deserializer);
	}

}
