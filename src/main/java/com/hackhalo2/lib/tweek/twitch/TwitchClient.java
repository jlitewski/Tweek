package com.hackhalo2.lib.tweek.twitch;

import com.hackhalo2.lib.tweek.IClient;
import com.hackhalo2.lib.tweek.oauth.IOAuthToken;

/**
 * The core component to all Twitch related API calls and functions
 * 
 * @author HACKhalo2
 *
 */
public final class TwitchClient implements IClient {
	
	private final String cilentID;
	
	private final String clientSecret;
	
	private IOAuthToken token;
	
	public TwitchClient(String clientID, String clientSecret) {
		this.cilentID = clientID;
		this.clientSecret = clientSecret;
	}

	@Override
	public String getClientID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getClientSecret() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IOAuthToken getOAuthToken() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
