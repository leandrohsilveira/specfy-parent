package com.github.leandrohsilveira.specfy.serializers;

import java.nio.charset.Charset;

import com.github.leandrohsilveira.specfy.Serializer;

public abstract class AbstracJsonSerializer implements Serializer {

	protected Charset charset;

	public AbstracJsonSerializer(Charset charset) {
		super();
		this.charset = charset;
	}

	public String getContentType() {
		return "application/json";
	}

}
