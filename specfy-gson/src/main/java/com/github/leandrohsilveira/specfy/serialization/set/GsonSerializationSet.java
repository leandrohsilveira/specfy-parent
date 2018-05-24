package com.github.leandrohsilveira.specfy.serialization.set;

import com.github.leandrohsilveira.specfy.RESTfulClientSpec;
import com.github.leandrohsilveira.specfy.SerializationSet;
import com.github.leandrohsilveira.specfy.serialization.deserializers.GsonDeserializer;
import com.github.leandrohsilveira.specfy.serialization.serializers.GsonSerializer;
import com.github.leandrohsilveira.specfy.serialization.utils.GsonSerializationUtils;
import com.google.gson.GsonBuilder;

public class GsonSerializationSet implements SerializationSet {
	
	public GsonSerializationSet() {
		this(GsonSerializationUtils.getDefaultGsonBuilder());
	}
	
	public GsonSerializationSet(GsonBuilder gsonBuilder) {
		this.gsonBuilder = gsonBuilder;
	}
	
	private GsonBuilder gsonBuilder;

	@Override
	public void register(RESTfulClientSpec client) {
		client.register(new GsonSerializer(gsonBuilder));
		client.register(new GsonDeserializer(gsonBuilder));
	}

}
