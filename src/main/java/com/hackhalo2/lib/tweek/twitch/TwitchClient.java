package com.hackhalo2.lib.tweek.twitch;

import com.hackhalo2.lib.tweek.ClientAuthPair;
import com.hackhalo2.lib.tweek.IClient;
import com.hackhalo2.lib.tweek.oauth.IOAuthToken;

/**
 * The core component to all Twitch related API calls and functions
 * 
 * @author HACKhalo2
 *
 */
public final class TwitchClient implements IClient {
	
	private final ClientAuthPair auth;
	
	private IOAuthToken token;
	
	public TwitchClient(String clientID, String clientSecret) {
		this.auth = new ClientAuthPair(clientID, clientSecret);
	}

	@Override
	public IOAuthToken getOAuthToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientAuthPair getClientAuth() {
		return this.auth;
	}
	
}
