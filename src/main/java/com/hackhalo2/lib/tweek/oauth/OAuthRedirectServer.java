package com.hackhalo2.lib.tweek.oauth;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.PassiveExpiringMap;

import fi.iki.elonen.NanoHTTPD;

public class OAuthRedirectServer extends NanoHTTPD {
	
	private final OAuthManager manager;
	
	private Timer timer = new Timer();
	
	private Map<String, OAuthRequest> requestCache = new PassiveExpiringMap<>(5, TimeUnit.MINUTES);
	
	protected OAuthRedirectServer(OAuthManager manager) {
		super(manager.getPort());
		this.manager = manager;
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
	
	public void cacheRequest(String key, OAuthRequest request) {
		this.requestCache.put(key, request);
	}
	
	@Override
	public Response serve(IHTTPSession session) {
		String reponseURI = session.getUri();
		Map<String, List<String>> queryParams = NanoHTTPD.decodeParameters(session.getQueryParameterString());
		IOAuthRedirectHandler handle = this.manager.getHandlerFromKey(reponseURI);
		List<String> states = queryParams.get(handle.getStateQueryString());
		
		OAuthRequest request = null;
		for(String state : states) {
			if(this.requestCache.containsKey(state)) {
				request = this.requestCache.get(state);
				break;
			}
		}
		
		if(request != null) {
			IOAuthToken token = handle.handleRedirect(request, queryParams);
			if(token != null) {
				this.manager.setOAuthToken(token);
				//TODO: return sexy success Response
				return newFixedLengthResponse("Success");
			}
			//TODO: Send sexy Failure-NullToken Response
			return newFixedLengthResponse("Failure - Token was null");
		}
		//TODO: Send sexy Failure-Timeout Response
		return newFixedLengthResponse("Failure - Request Timedout");
		
	}

}
