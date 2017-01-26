package com.github.leandrohsilveira.specfy.serialization.serializers;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;

import com.github.leandrohsilveira.specfy.Serializer;

public class ObjectSerializer implements Serializer {

	@Override
	public void serialize(Object obj, OutputStream requestBody, Charset charset) throws IOException {
		if (obj != null) IOUtils.write(obj.toString(), requestBody, charset);
	}

}
