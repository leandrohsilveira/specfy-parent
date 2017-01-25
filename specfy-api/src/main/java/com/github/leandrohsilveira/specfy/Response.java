package com.github.leandrohsilveira.specfy;

import java.io.Closeable;
import java.io.InputStream;
import java.util.List;

public interface Response extends Closeable {

	List<String> getHeader(Header header);

	String getHeader(Header header, int index);

	int getStatus();

	String getStatusText();

	InputStream getBody();

}
