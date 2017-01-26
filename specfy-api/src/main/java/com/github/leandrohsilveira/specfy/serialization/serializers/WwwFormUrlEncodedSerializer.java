package com.github.leandrohsilveira.specfy.serialization.serializers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.github.leandrohsilveira.specfy.Serializer;
import com.github.leandrohsilveira.specfy.serialization.WwwFormUrlEncoded;

public class WwwFormUrlEncodedSerializer implements Serializer {

	@Override
	public void serialize(Object obj, OutputStream requestBody, Charset charset) throws IOException {
		String value = ((WwwFormUrlEncoded) obj).toString();
		IOUtils.write(URLEncoder.encode(value, charset.name()), requestBody, charset);
	}

}
