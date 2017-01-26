package com.github.leandrohsilveira.specfy.serialization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.github.leandrohsilveira.specfy.RESTfulClientSpec;
import com.github.leandrohsilveira.specfy.Serializer;
import com.github.leandrohsilveira.specfy.serialization.sets.JsonSimpleSerializationSet;

public class JsonSimpleSerializationTest {

	private static final Charset CHARSET = Charset.forName("UTF-8");

	private static RESTfulClientSpec client;

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@BeforeClass
	public static void setUp() {
		client = new RESTfulClientSpec("/test").register(new JsonSimpleSerializationSet());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void jsonObjectSerializer_singleLevel_Test() throws Exception {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "Specfy RESTful Client Test");
		jsonObject.put("age", 0);

		serialize(jsonObject);
	}

	@Test
	@SuppressWarnings("unchecked")
	public void jsonObjectSerializer_multiLevel_Test() throws Exception {
		JSONArray array = new JSONArray();
		array.addAll(Arrays.asList("Leandro Hinckel Silveira"));

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", "Specfy RESTful Client Test");
		jsonObject.put("age", 0);
		jsonObject.put("colaborators", array);

		serialize(jsonObject);
	}

	private void serialize(Object object) throws IOException, FileNotFoundException {
		Serializer serializer = client.getSerializer(object.getClass());
		assertNotNull(serializer);

		File file = temporaryFolder.newFile("jsonObjectSerializerTest.json");

		try (FileOutputStream outputStream = new FileOutputStream(file)) {
			serializer.serialize(object, outputStream, CHARSET);
			outputStream.flush();
		}

		try (FileInputStream inputStream = new FileInputStream(file)) {
			assertEquals(JSONValue.toJSONString(object), IOUtils.toString(inputStream, CHARSET));
		}
	}

}
