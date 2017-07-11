package com.hackhalo2.lib.tweek.oauth;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;

public class OAuthRedirectServer extends NanoHTTPD {
	
	public OAuthRedirectServer() throws IOException {
		super(7090);
		this.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
	}
	
	@Override
	public Response serve(IHTTPSession session) {
		
	}

}
