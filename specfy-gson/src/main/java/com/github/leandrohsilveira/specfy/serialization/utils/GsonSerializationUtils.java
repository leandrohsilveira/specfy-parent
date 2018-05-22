package com.github.leandrohsilveira.specfy.serialization.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonSerializationUtils {

	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	private GsonSerializationUtils() {
	}

	public static final Gson GSON = new GsonBuilder().setDateFormat(DATE_FORMAT).create();

	public static String formatDate(Date date) {
		return new SimpleDateFormat(DATE_FORMAT).format(date);
	}
	
}
