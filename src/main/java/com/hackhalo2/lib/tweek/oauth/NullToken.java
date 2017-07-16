package com.hackhalo2.lib.tweek.oauth;

import java.util.List;

public class NullToken implements IOAuthToken {
	
	protected NullToken() {
		
	}

	@Override
	public String getToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTokenType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getScopes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasScope(String scope) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

}
