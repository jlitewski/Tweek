package com.hackhalo2.lib.tweek.oauth;

import java.net.URI;
import java.net.URISyntaxException;

public interface IOAuthHandler {

	public IOAuthToken handleRedirectResponse(String authResponse);
	
	public URI getRedirectURI() throws URISyntaxException;
	
	public URI getAuthenticationURI(String type, String... scopes) throws URISyntaxException;
}
