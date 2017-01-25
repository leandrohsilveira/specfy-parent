package com.github.leandrohsilveira.specfy;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import com.github.leandrohsilveira.specfy.engines.net.NetURLConnectionEngine;
import com.github.leandrohsilveira.specfy.serialization.serializers.WwwFormUrlEncodedSerializer;
import com.github.leandrohsilveira.specfy.serialization.set.DefaultStringSerializationSet;
import com.github.leandrohsilveira.specfy.utils.SpecfyUtils;

public class RESTfulClientSpec {

	public RESTfulClientSpec(String resourcesRoot) {
		this(null, resourcesRoot);
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
	protected Map<String, Serializer> defaultContentSerializers;
	protected Map<String, Deserializer> defaultContentDeserializers;
	protected Engine engine = new NetURLConnectionEngine();
	protected SSLContext defaultSslContext;

	protected Charset charset = Charset.forName("UTF-8");

	protected String host = "http://localhost:8080";

	@Override
	public String toString() {
		return String.format("RESTful Client Root: /%s", resourcesRoot);
	}

	private void registerDefaultSerializers() {
		WwwFormUrlEncodedSerializer serializer = new WwwFormUrlEncodedSerializer();
		register(serializer);
		registerDefault(serializer);
		register(new DefaultStringSerializationSet());
	}

	public Serializer getSerializer(Class<?> clazz) {
		return serializers != null ? serializers.get(clazz) : null;
	}

	public Deserializer getDeserializer(Class<?> clazz) {
		return deserializers != null ? deserializers.get(clazz) : null;
	}

	public RESTfulClientSpec register(Serializer serializer) {
		if (this.serializers == null) this.serializers = new HashMap<>();
		this.serializers.put(serializer.getSerializableClass(), serializer);
		return this;
	}

	public RESTfulClientSpec register(Deserializer deserializer) {
		if (this.deserializers == null) this.deserializers = new HashMap<>();
		this.deserializers.put(deserializer.getSerializableClass(), deserializer);
		return this;
	}

	public RESTfulClientSpec registerDefault(Serializer serializer) {
		if (this.defaultContentSerializers == null) this.defaultContentSerializers = new HashMap<>();
		this.defaultContentSerializers.put(serializer.getContentType(), serializer);
		return this;
	}

	public RESTfulClientSpec registerDefault(Deserializer deserializer) {
		if (this.defaultContentDeserializers == null) this.defaultContentDeserializers = new HashMap<>();
		this.defaultContentDeserializers.put(deserializer.getContentType(), deserializer);
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

	public Deserializer getDeserializer(String contentType) {
		return defaultContentDeserializers != null ? defaultContentDeserializers.get(contentType) : null;
	}

	public Serializer getSerializer(String contentType) {
		return defaultContentSerializers != null ? defaultContentSerializers.get(contentType) : null;
	}

}
