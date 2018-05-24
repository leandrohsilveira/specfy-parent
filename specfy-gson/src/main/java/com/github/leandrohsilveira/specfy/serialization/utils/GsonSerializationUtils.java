package com.github.leandrohsilveira.specfy.serialization.utils;

import com.google.gson.GsonBuilder;

public class GsonSerializationUtils {

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	private GsonSerializationUtils() {
	}

	public static GsonBuilder getDefaultGsonBuilder() {
		return new GsonBuilder().setDateFormat(GsonSerializationUtils.DEFAULT_DATE_FORMAT);
	}
	
}
