package com.hackhalo2.lib.tweek;

public final class ClientAuthPair {
	
	private final String clientID;
	
	private final String clientSecret;
	
	public ClientAuthPair(final String clientID, final String clientSecret) {
		this.clientID = clientID;
		this.clientSecret = clientSecret;
	}
	
	public String getClientID() {
		return this.clientID;
	}
	
	public String getClientSecret() {
		return this.clientSecret;
	}

}
