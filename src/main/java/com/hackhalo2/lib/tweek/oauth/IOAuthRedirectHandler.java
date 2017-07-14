package com.hackhalo2.lib.tweek.oauth;

import java.util.List;
import java.util.Map;

public interface IOAuthRedirectHandler {
	
	public default String getStateQueryString() { return "state"; }
	
	public String getRedirectKey();
	
	public IOAuthToken handleRedirect(OAuthRequest request, Map<String, List<String>> queryParams);

}
