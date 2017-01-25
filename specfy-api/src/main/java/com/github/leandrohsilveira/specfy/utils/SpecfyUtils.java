package com.github.leandrohsilveira.specfy.utils;

import java.util.regex.Pattern;

public class SpecfyUtils {

	public static String safeConcat(String baseString, String concat) {
		baseString = removeLastDash(baseString);
		concat = removeFirstDash(concat);
		String result = baseString.concat("/").concat(concat);
		result = removeFirstDash(result);
		result = removeLastDash(result);
		return result;
	}

	public static String removeLastDash(String url) {
		return url.replaceAll("\\/$", "");
	}

	public static String removeFirstDash(String url) {
		return url.replaceAll("^\\/", "");
	}

	public static boolean endsWithDash(String url) {
		return Pattern.matches(".+\\/$", url);
	}

	public static boolean startsWithDash(String url) {
		return Pattern.matches("^\\/.+", url);
	}

}
