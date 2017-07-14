package com.hackhalo2.lib.tweek.oauth;

import java.util.List;

public abstract class AbstractOAuthToken implements IOAuthToken {
	
	private final String id;
	private final List<String> scopes;
	
	protected AbstractOAuthToken(OAuthRequest request, List<String> scopes) {
		this.id = request.getID();
		this.scopes = scopes;
	}

	@Override
	public String getID() {
		return this.id;
	}

	@Override
	public List<String> getScopes() {
		return this.scopes;
	}

	@Override
	public boolean tokenHasScope(String scope) {
		return this.scopes.contains(scope);
	}

}
