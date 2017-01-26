package com.github.leandrohsilveira.specfy.serialization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.github.leandrohsilveira.specfy.Deserializer;
import com.github.leandrohsilveira.specfy.RESTfulClientSpec;
import com.github.leandrohsilveira.specfy.Serializer;
import com.github.leandrohsilveira.specfy.serialization.set.GsonSerializationSet;
import com.github.leandrohsilveira.specfy.serialization.utils.GsonSerializationUtils;

public class GsonSerializationTest {

	private static final Charset CHARSET = Charset.forName("UTF-8");

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	private static RESTfulClientSpec client;

	@BeforeClass
	public static void setUp() {
		client = new RESTfulClientSpec("/test").register(new GsonSerializationSet());
	}

	@Test
	public void serializationTest() throws Exception {
		File file = temporaryFolder.newFile("jsonObjectSerializerTest.json");
		Project project = new Project("Specfy RESTful Client Test", 0, Arrays.asList("Leandro Hinckel Silveira"));
		serialize(file, project);
		deserialize(file, project);
	}

	private void serialize(File file, Object object) throws Exception {
		Serializer serializer = client.getSerializer(object.getClass());
		assertNotNull(serializer);

		try (FileOutputStream outputStream = new FileOutputStream(file)) {
			serializer.serialize(object, outputStream, CHARSET);
			outputStream.flush();
		}

		try (FileInputStream inputStream = new FileInputStream(file)) {
			assertEquals(GsonSerializationUtils.GSON.toJson(object), IOUtils.toString(inputStream, CHARSET));
		}
	}

	private void deserialize(File file, Object object) throws Exception {
		Deserializer deserializer = client.getDeserializer(object.getClass());
		assertNotNull(deserializer);

		try (FileInputStream inputStream = new FileInputStream(file)) {
			assertEquals(object, deserializer.deserialize(inputStream, object.getClass(), CHARSET));
		}
	}

	public static class Project {

		public Project() {
		}

		public Project(String name, int age, List<String> colaborators) {
			super();
			this.name = name;
			this.age = age;
			this.colaborators = colaborators;
		}

		public String name;
		public int age;
		public List<String> colaborators;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + age;
			result = prime * result + ((colaborators == null) ? 0 : colaborators.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null) return false;
			if (!(obj instanceof Project)) return false;
			Project other = (Project) obj;
			if (age != other.age) return false;
			if (colaborators == null) {
				if (other.colaborators != null) return false;
			} else if (!colaborators.equals(other.colaborators)) return false;
			if (name == null) {
				if (other.name != null) return false;
			} else if (!name.equals(other.name)) return false;
			return true;
		}

	}

}
