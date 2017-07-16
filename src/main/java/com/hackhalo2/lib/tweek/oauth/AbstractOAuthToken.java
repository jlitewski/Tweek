package com.hackhalo2.lib.tweek.oauth;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOAuthToken implements IOAuthToken {
	
	private final String id;
	private final List<String> scopes;
	
	protected AbstractOAuthToken(OAuthRequest request, String... inScopes) {
		this.id = request.getID();
		this.scopes = new ArrayList<>();
		
		for(String scope : inScopes) {
			this.scopes.add(scope.toLowerCase());
		}
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
	public boolean hasScope(String scope) {
		return this.scopes.contains(scope.toLowerCase());
	}

}
