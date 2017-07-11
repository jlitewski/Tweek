package com.hackhalo2.lib.tweek.oauth;

import java.net.URI;

public interface IOAuthHandler {

	public IOAuthToken handleRedirectResponse(String authResponse);
	
	public URI getRedirectURI();
	
	public URI getAuthenticationURI(String type, String... scopes);
}
