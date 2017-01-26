package com.github.leandrohsilveira.specfy;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import com.github.leandrohsilveira.specfy.engines.net.NetURLConnectionEngine;
import com.github.leandrohsilveira.specfy.serialization.WwwFormUrlEncoded;
import com.github.leandrohsilveira.specfy.serialization.serializers.ObjectSerializer;
import com.github.leandrohsilveira.specfy.serialization.serializers.WwwFormUrlEncodedSerializer;
import com.github.leandrohsilveira.specfy.serialization.set.StringSerializationSet;
import com.github.leandrohsilveira.specfy.utils.SpecfyUtils;

public class RESTfulClientSpec {

	public RESTfulClientSpec(String resourcesRoot) {
		this("http://localhost:8080", resourcesRoot);
	}

	@Deprecated
	protected RESTfulClientSpec(String host, String resourcesRoot) {
		this.host = host;
		this.resourcesRoot = SpecfyUtils.removeFirstDash(SpecfyUtils.removeLastDash(resourcesRoot));
		registerDefaultSerializers();
	}

	protected String resourcesRoot;
	protected Map<Class<?>, Serializer> serializers;
	protected Map<Class<?>, Deserializer> deserializers;
	protected Serializer defaultObjectSerializer;
	protected Deserializer defaultObjectDeserializer;

	protected Engine engine = new NetURLConnectionEngine();
	protected SSLContext defaultSslContext;

	protected Charset charset = Charset.forName("UTF-8");

	protected String host;

	@Override
	public String toString() {
		return String.format("RESTful Client Root: /%s", resourcesRoot);
	}

	private void registerDefaultSerializers() {
		register(WwwFormUrlEncoded.class, new WwwFormUrlEncodedSerializer());
		register(new StringSerializationSet());
		register(new ObjectSerializer());
	}

	public RESTfulClientSpec register(Class<?> forClass, Serializer serializer) {
		if (this.serializers == null) this.serializers = new HashMap<>();
		this.serializers.put(forClass, serializer);
		return this;
	}

	public RESTfulClientSpec register(Class<?> forClass, Deserializer deserializer) {
		if (this.deserializers == null) this.deserializers = new HashMap<>();
		this.deserializers.put(forClass, deserializer);
		return this;
	}

	public RESTfulClientSpec register(Serializer serializer) {
		this.defaultObjectSerializer = serializer;
		return this;
	}

	public RESTfulClientSpec register(Deserializer deserializer) {
		this.defaultObjectDeserializer = deserializer;
		return this;
	}

	public RESTfulClientSpec register(SerializationSet serializationSet) {
		serializationSet.register(this);
		return this;
	}

	public ResourceSpec resource(String resource) {
		return new ResourceSpec(this, resource);
	}

	public ResourceSpec resource() {
		return resource("");
	}

	public Charset getCharset() {
		return charset;
	}

	public RESTfulClientSpec setEngine(Engine engine) {
		this.engine = engine;
		return this;
	}

	public RESTfulClientSpec setCharset(Charset charset) {
		this.charset = charset;
		return this;
	}

	public RESTfulClientSpec setDefaultSslContext(SSLContext defaultSslContext) {
		this.defaultSslContext = defaultSslContext;
		return this;
	}

	public Serializer getSerializer(Class<?> clazz) {
		if (serializers != null) {
			Serializer serializer = serializers.get(clazz);
			if (serializer != null)
				return serializer;
		}
		return getDefaultObjectSerializer();
	}

	public Deserializer getDeserializer(Class<?> clazz) {
		if (deserializers != null) {
			Deserializer deserializer = deserializers.get(clazz);
			if (deserializer != null)
				return deserializer;
		}
		return getDefaultObjectDeserializer();
	}

	public Deserializer getDefaultObjectDeserializer() {
		return defaultObjectDeserializer;
	}

	public Serializer getDefaultObjectSerializer() {
		return defaultObjectSerializer;
	}

}
