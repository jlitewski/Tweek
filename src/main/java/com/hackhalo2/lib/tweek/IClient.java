package com.hackhalo2.lib.tweek;

import com.hackhalo2.lib.tweek.oauth.IOAuthToken;

public interface IClient {
	
	public ClientAuthPair getClientAuth();
	
	public IOAuthToken getOAuthToken();

}
