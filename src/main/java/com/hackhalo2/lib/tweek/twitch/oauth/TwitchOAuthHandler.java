package com.hackhalo2.lib.tweek.twitch.oauth;

import java.net.URI;
import java.net.URISyntaxException;

import com.hackhalo2.lib.tweek.oauth.IOAuthHandler;
import com.hackhalo2.lib.tweek.oauth.IOAuthToken;
import com.hackhalo2.lib.tweek.oauth.OAuthManager;

public class TwitchOAuthHandler implements IOAuthHandler {

	@Override
	public URI getRedirectURI() throws URISyntaxException {
		return new URI(String.format(OAuthManager.serverAddress+"oauth_authorize_twitch", OAuthManager.serverPort));
	}

	@Override
	public URI getAuthenticationURI(String type, String... scopes) throws URISyntaxException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public IOAuthToken handleRedirectResponse(String authResponse) {
		// TODO Auto-generated method stub
		return null;
	}

}
