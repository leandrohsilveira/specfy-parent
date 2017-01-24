package com.github.leandrohsilveira.specfy;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;

import com.github.leandrohsilveira.specfy.Spec.SpecSchema.SpecHost;
import com.github.leandrohsilveira.specfy.engines.net.NetURLConnectionEngine;
import com.github.leandrohsilveira.specfy.serialization.serializers.DefaultStringSerializationSet;
import com.github.leandrohsilveira.specfy.serialization.serializers.WwwFormUrlEncodedSerializer;

public class RESTfulClientSpec {

	protected String resourcesRoot;
	protected Map<Class<?>, Serializer> serializers;
	protected Map<Class<?>, Deserializer<?>> deserializers;
	protected Engine engine = new NetURLConnectionEngine();
	protected SSLContext defaultSslContext;

	protected Charset charset = Charset.forName("UTF-8");

	@Override
	public String toString() {
		return String.format("RESTful Client Root: %s", resourcesRoot);
	}

	protected RESTfulClientSpec(SpecHost resourceHost, String resourcesRoot) {
		this.resourcesRoot = safeConcat(resourceHost.compose(), resourcesRoot);
		register(new DefaultStringSerializationSet());
		register(new WwwFormUrlEncodedSerializer(this.charset));
	}

	protected static String safeConcat(String baseString, String concat) {
		String base = baseString;
		if (!endsWithDash(baseString)) {
			base = baseString.concat("/");
		}
		return base.concat(concat);
	}

	protected static boolean endsWithDash(String resourcesRoot) {
		return Pattern.matches(".+\\/$", resourcesRoot);
	}

	public Serializer getSerializer(Class<?> clazz) {
		return serializers != null ? serializers.get(clazz) : null;
	}

	public Deserializer<?> getDeserializer(Class<?> clazz) {
		return deserializers != null ? deserializers.get(clazz) : null;
	}

	public RESTfulClientSpec register(Serializer serializer) {
		if (this.serializers == null) this.serializers = new HashMap<>();
		this.serializers.put(serializer.getSerializableClass(), serializer);
		return this;
	}

	public RESTfulClientSpec register(Deserializer<?> deserializer) {
		if (this.deserializers == null) this.deserializers = new HashMap<>();
		this.deserializers.put(deserializer.getSerializableClass(), deserializer);
		return this;
	}

	public RESTfulClientSpec register(SerializationSet set) {
		set.register(this);
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

}
