package com.hackhalo2.lib.tweek.oauth;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OAuthManager {
	
	private static final String serverAddress = "http://localhost:%d/";
	private static int serverPort = 7090;
	private Map<String, IOAuthHandler> oauthHandlers = new LinkedHashMap<>();
	private Map<String, IOAuthRedirectHandler> redirectHandlers = new LinkedHashMap<>();
	private Map<String, IOAuthToken> oauthTokens = new LinkedHashMap<>();
	private Map<String, String> idToTokenKey = new HashMap<>();
	private OAuthRedirectServer redirectServer;
	
	public OAuthManager() {
		this.redirectServer = new OAuthRedirectServer(this);
	}
	
	public void requestPermissions(String handlerKey, String oauthKey, String... scopes) {
		IOAuthHandler handler = this.oauthHandlers.get(handlerKey.toLowerCase());
		
		if(handler != null) {
			try {
				this.redirectServer.listenForRedirect();
				
				OAuthRequest request = handler.requestPermissions(scopes);
				this.redirectServer.cacheRequest(request.getState(), request); //cache it on the redirect server
				this.idToTokenKey.put(request.getID(), oauthKey);
				this.oauthTokens.put(oauthKey, new NullToken()); //get the token map ready for the actual token
				
				String requestURL = handler.getAuthenticationURI(request);
				
				Desktop desktop = null;
				if(Desktop.isDesktopSupported()) {
					desktop = Desktop.getDesktop();
					if(desktop.isSupported(Desktop.Action.BROWSE)) { //God I hope this is always true
						desktop.browse(new URI(requestURL));
					} else {
						//XXX: Should I do some hackery and make a JWebPane?
						throw new RuntimeException("AWT Desktop Browse not supported!");
					}
				}
			} catch (IOException | URISyntaxException e) {
				//TODO: Log and complain
			}
		}
		
		//TODO: Log and complain heavily
	}
	
	public boolean registerOAuthHandler(IOAuthHandler handler) {
		return this.registerHandler(handler, false);
	}
	
	public boolean registerHandler(IOAuthHandler handler, boolean overwrite) {
		if(!this.oauthHandlers.containsKey(handler.getHandlerKey().toLowerCase()) || overwrite) {
			this.oauthHandlers.put(handler.getHandlerKey().toLowerCase(), handler);
			return true;
		} else return false;
	}
	
	public boolean registerOAuthRedirectHandler(IOAuthRedirectHandler handler) {
		return this.registerOAuthRedirectHandler(handler, false);
	}
	
	public boolean registerOAuthRedirectHandler(IOAuthRedirectHandler handler, boolean overwrite) {
		if(!this.redirectHandlers.containsKey(handler.getRedirectKey().toLowerCase()) || overwrite) {
			this.redirectHandlers.put(handler.getRedirectKey().toLowerCase(), handler);
			return true;
		} else return false;
	}
	
	protected IOAuthRedirectHandler getHandlerFromKey(String key) {
		return this.redirectHandlers.get(key.toLowerCase());
	}
	
	protected int getPort() {
		return OAuthManager.serverPort;
	}
	
	protected void setOAuthToken(IOAuthToken token) {
		String oauthKey = this.idToTokenKey.get(token.getID());
		this.oauthTokens.put(oauthKey, token);
	}
	
	public IOAuthToken getOAuthToken(String oauthKey) {
		return this.oauthTokens.get(oauthKey);
	}
	
	public static void setListeningPort(final int port) {
		OAuthManager.serverPort = port;
	}
	
	public static String getRedirectAddress() {
		return String.format(OAuthManager.serverAddress, OAuthManager.serverPort);
	}

}
