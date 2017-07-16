package com.hackhalo2.lib.tweek.oauth;

import java.util.List;

public interface IOAuthToken {
	
	public default boolean canTokenExpire() { return false; }
	
	public default boolean isTokenExpired() { return false; }
	
	public String getID();
	
	public String getToken();
	
	public String getTokenType();
	
	public List<String> getScopes();
	
	public boolean hasScope(String scope);

}
