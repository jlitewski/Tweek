package com.hackhalo2.lib.tweek.oauth;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.hackhalo2.lib.tweek.utils.TweekUtils;

public final class OAuthRequest {

	private Set<String> scopes = new HashSet<>();
	
	private final String tokenID;
	
	private final String tokenType;
	
	private final String state;
	
	public OAuthRequest(String type) {
		this.tokenID = UUID.randomUUID().toString();
		this.tokenType = type;
		this.state = TweekUtils.generateNonce();
	}
	
	public final String getID() {
		return this.tokenID;
	}
	
	public final String getType() {
		return this.tokenType;
	}
	
	public final String getState() {
		return this.state;
	}
	
	public Collection<String> getScopes() {
		return Collections.unmodifiableSet(this.scopes);
	}
	
	public void addScopes(String... scopes) {
		for(String scope : scopes) {
			if(!this.scopes.contains(scope)) this.scopes.add(scope);
		}
	}
}
