package com.hackhalo2.lib.tweek.twitch.oauth;

import com.hackhalo2.lib.tweek.oauth.AbstractOAuthToken;
import com.hackhalo2.lib.tweek.oauth.OAuthRequest;

public class TwitchOAuthToken extends AbstractOAuthToken {
	
	private String token;
	
	protected TwitchOAuthToken(String token, OAuthRequest request, String... scopes) {
		super(request, scopes);
		this.token = token;
	}

	@Override
	public String getToken() {
		return this.token;
	}

	@Override
	public String getTokenType() {
		return "TWITCH";
	}

}
