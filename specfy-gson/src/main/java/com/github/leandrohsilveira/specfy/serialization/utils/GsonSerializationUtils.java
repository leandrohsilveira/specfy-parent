package com.github.leandrohsilveira.specfy.serialization.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonSerializationUtils {

	private GsonSerializationUtils() {
	}

	public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

}
