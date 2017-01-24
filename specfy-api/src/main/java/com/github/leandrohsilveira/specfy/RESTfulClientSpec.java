package com.github.leandrohsilveira.specfy;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;

import com.github.leandrohsilveira.specfy.Spec.SpecSchema.SpecHost;
import com.github.leandrohsilveira.specfy.engines.net.NetURLConnectionEngine;

public class RESTfulClientSpec {

	protected String resourcesRoot;
	protected Map<Class<?>, Serializer> serializers;
	protected Map<Class<?>, Deserializer<?>> deserializers;
	protected Engine engine = new NetURLConnectionEngine();
	protected SSLContext defaultSslContext;

	protected Charset charset = Charset.forName("UTF-8");

	protected RESTfulClientSpec(SpecHost resourceHost, String resourcesRoot) {
		this.resourcesRoot = safeConcat(resourceHost.compose(), resourcesRoot);
		register(new DefaultStringSerializer());
		register(new DefaultStringDeserializer());
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

	protected class DefaultStringSerializer implements Serializer {

		@Override
		public String getContentType() {
			return "text/plain";
		}

		@Override
		public void serialize(Object obj, OutputStream requestBody) throws IOException {
			IOUtils.write(((String) obj), requestBody, RESTfulClientSpec.this.charset);
		}

		@Override
		public Class<?> getSerializableClass() {
			return String.class;
		}

	}

	protected class DefaultStringDeserializer implements Deserializer<String> {

		@Override
		public String getContentType() {
			return "text/plain";
		}

		@Override
		public String deserialize(InputStream responseInput) throws IOException {
			try {
				return IOUtils.toString(responseInput, RESTfulClientSpec.this.charset);
			} finally {
				IOUtils.closeQuietly(responseInput);
			}
		}

		@Override
		public Class<String> getSerializableClass() {
			return String.class;
		}

	}

}
