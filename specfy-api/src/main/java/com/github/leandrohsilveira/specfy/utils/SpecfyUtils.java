package com.github.leandrohsilveira.specfy.utils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
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

	public static String appendQueryParameter(String url, String paramName, Charset charset, Object... values) throws IOException {
		return appendQueryParameter(url, paramName, Arrays.asList(values), charset);
	}

	public static String appendQueryParameter(String url, String paramName, List<?> values, Charset charset) throws IOException {
		StringBuilder rawUrl = new StringBuilder(url);
		if (values != null) for (Object value : values) {
			if (Pattern.matches("^.+\\?.+$", rawUrl)) {
				rawUrl.append("&");
			} else {
				rawUrl.append("?");
			}
			rawUrl.append(paramName).append("=").append(URLEncoder.encode(value.toString(), charset.name()));
		}
		return rawUrl.toString();
	}

}
