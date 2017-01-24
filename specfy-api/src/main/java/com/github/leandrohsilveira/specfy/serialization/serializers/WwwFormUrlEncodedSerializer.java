package com.github.leandrohsilveira.specfy.serialization.serializers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.github.leandrohsilveira.specfy.Serializer;
import com.github.leandrohsilveira.specfy.serialization.WwwFormUrlEncoded;

public class WwwFormUrlEncodedSerializer implements Serializer {

	public WwwFormUrlEncodedSerializer(Charset charset) {
		this.charset = charset;
	}

	private Charset charset;

	@Override
	public Class<?> getSerializableClass() {
		return WwwFormUrlEncoded.class;
	}

	@Override
	public String getContentType() {
		return "application/x-www-form-urlencoded";
	}

	@Override
	public void serialize(Object obj, OutputStream requestBody) throws IOException {
		String value = ((WwwFormUrlEncoded) obj).toString();
		IOUtils.write(URLEncoder.encode(value, charset.name()), requestBody, charset);
	}

}
