package com.hackhalo2.lib.tweek.oauth;

public interface IOAuthHandler {
	
	public String getHandlerKey();
	
	public OAuthRequest requestPermissions(String... scopes);
	
	public String getRedirectURI();
	
	public String getAuthenticationURI(OAuthRequest request);
}
