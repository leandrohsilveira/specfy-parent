package com.github.leandrohsilveira.specfy.deserializers;

import java.nio.charset.Charset;

import com.github.leandrohsilveira.specfy.Deserializer;

public abstract class AbstracJsonDeserializer<T> implements Deserializer<T> {

	protected Charset charset;

	public AbstracJsonDeserializer(Charset charset) {
		super();
		this.charset = charset;
	}

	public String getContentType() {
		return "application/json";
	}

}
