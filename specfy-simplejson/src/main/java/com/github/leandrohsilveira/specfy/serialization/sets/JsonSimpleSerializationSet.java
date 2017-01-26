package com.github.leandrohsilveira.specfy.serialization.sets;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.github.leandrohsilveira.specfy.RESTfulClientSpec;
import com.github.leandrohsilveira.specfy.SerializationSet;
import com.github.leandrohsilveira.specfy.serialization.deserializers.JSONArrayDeserializer;
import com.github.leandrohsilveira.specfy.serialization.deserializers.JSONObjectDeserializer;
import com.github.leandrohsilveira.specfy.serialization.serializers.JSONArraySerializer;
import com.github.leandrohsilveira.specfy.serialization.serializers.JSONObjectSerializer;

public class JsonSimpleSerializationSet implements SerializationSet {

	@Override
	public void register(RESTfulClientSpec client) {
		client.register(JSONArray.class, new JSONArraySerializer());
		client.register(JSONObject.class, new JSONObjectSerializer());

		client.register(JSONArray.class, new JSONArrayDeserializer());
		client.register(JSONObject.class, new JSONObjectDeserializer());

	}

}
