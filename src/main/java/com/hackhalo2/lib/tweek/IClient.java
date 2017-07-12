package com.hackhalo2.lib.tweek;

import com.hackhalo2.lib.tweek.oauth.IOAuthToken;

public interface IClient {
	
	public String getClientID();
	
	public String getClientSecret();
	
	public IOAuthToken getOAuthToken();

}
