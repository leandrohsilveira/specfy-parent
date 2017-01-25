package com.github.leandrohsilveira.specfy.serialization.serializers;

import com.github.leandrohsilveira.specfy.Serializer;

public abstract class AbstracJsonSerializer implements Serializer {

	public String getContentType() {
		return "application/json";
	}

}
