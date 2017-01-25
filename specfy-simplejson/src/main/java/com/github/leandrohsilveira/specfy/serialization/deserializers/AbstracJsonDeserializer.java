package com.github.leandrohsilveira.specfy.serialization.deserializers;

import com.github.leandrohsilveira.specfy.Deserializer;

public abstract class AbstracJsonDeserializer implements Deserializer {

	@Override
	public String getContentType() {
		return "application/json";
	}

}
