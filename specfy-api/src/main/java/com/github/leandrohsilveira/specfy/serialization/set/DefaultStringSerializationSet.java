package com.github.leandrohsilveira.specfy.serialization.set;

import com.github.leandrohsilveira.specfy.RESTfulClientSpec;
import com.github.leandrohsilveira.specfy.SerializationSet;
import com.github.leandrohsilveira.specfy.serialization.deserializers.DefaultStringDeserializer;
import com.github.leandrohsilveira.specfy.serialization.serializers.DefaultStringSerializer;

public class DefaultStringSerializationSet implements SerializationSet {

	@Override
	public void register(RESTfulClientSpec client) {
		DefaultStringSerializer serializer = new DefaultStringSerializer();
		DefaultStringDeserializer deserializer = new DefaultStringDeserializer();

		client.register(serializer);
		client.register(deserializer);
		client.registerDefault(serializer);
		client.registerDefault(deserializer);
	}

}
