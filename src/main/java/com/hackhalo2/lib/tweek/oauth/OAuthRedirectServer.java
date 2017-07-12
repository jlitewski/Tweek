package com.hackhalo2.lib.tweek.oauth;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import fi.iki.elonen.NanoHTTPD;

public class OAuthRedirectServer extends NanoHTTPD {
	
	private Timer timer = new Timer();
	
	public OAuthRedirectServer(final int port) throws IOException {
		super(port);
	}
	
	public void listenForRedirect() throws IOException {
		if(!this.isAlive()) {
			this.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
			this.timer.schedule(new TimerTask()
			{
				@Override
				public void run() {
					if(OAuthRedirectServer.this.isAlive()) {
						OAuthRedirectServer.this.stop();
					}
				}
				
			}, 600000);
		}
	}
	
	@Override
	public Response serve(IHTTPSession session) {
		
	}

}
