package com.github.leandrohsilveira.specfy.serialization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WwwFormUrlEncoded {

	private Map<String, List<Object>> parameters = new HashMap<>();

	public WwwFormUrlEncoded bind(String paramName, Object paramValue) {
		if (this.parameters.containsKey(paramName)) this.parameters.get(paramName).add(paramValue);
		else
			this.parameters.put(paramName, new ArrayList<>(Arrays.asList(paramValue)));
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (this.parameters != null) for (Entry<String, List<Object>> formEntry : this.parameters.entrySet()) {
			for (Iterator<Object> valueIterator = formEntry.getValue().iterator(); valueIterator.hasNext();) {
				Object value = valueIterator.next();
				builder.append(formEntry.getKey()).append("=").append(value.toString());
				if (valueIterator.hasNext()) builder.append("&");
			}
		}
		return builder.toString();
	}

}
