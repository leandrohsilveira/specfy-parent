package com.github.leandrohsilveira.specfy.engines.net;

import java.io.IOException;
import java.nio.charset.Charset;

import com.github.leandrohsilveira.specfy.AbstractEngine;
import com.github.leandrohsilveira.specfy.Engine;
import com.github.leandrohsilveira.specfy.Request;

public class NetURLConnectionEngine extends AbstractEngine implements Engine {

	@Override
	public Request createRequest(String url, Charset charset) throws IOException {
		return new NetURLConnectionRequest(url, charset);
	}

}
