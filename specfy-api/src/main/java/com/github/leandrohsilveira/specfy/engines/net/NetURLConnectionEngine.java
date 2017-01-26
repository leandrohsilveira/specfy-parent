package com.github.leandrohsilveira.specfy.engines.net;

import java.io.IOException;
import java.nio.charset.Charset;

import com.github.leandrohsilveira.specfy.AbstractEngine;
import com.github.leandrohsilveira.specfy.Engine;
import com.github.leandrohsilveira.specfy.RequestSpec;

public class NetURLConnectionEngine extends AbstractEngine<NetURLConnectionRequest> implements Engine {

	@Override
	protected NetURLConnectionRequest createRequest(RequestSpec requestSpec, String url, Charset charset) throws IOException {
		return new NetURLConnectionRequest(requestSpec, url, charset);
	}

}
