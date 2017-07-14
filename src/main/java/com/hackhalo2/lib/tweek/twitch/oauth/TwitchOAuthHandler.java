package com.hackhalo2.lib.tweek.twitch.oauth;

import java.util.List;
import java.util.Map;

import com.hackhalo2.lib.tweek.oauth.IOAuthHandler;
import com.hackhalo2.lib.tweek.oauth.IOAuthRedirectHandler;
import com.hackhalo2.lib.tweek.oauth.IOAuthToken;
import com.hackhalo2.lib.tweek.oauth.OAuthManager;
import com.hackhalo2.lib.tweek.oauth.OAuthRequest;
import com.hackhalo2.lib.tweek.twitch.TwitchScope;
import com.hackhalo2.lib.tweek.twitch.endpoints.TwitchURLEndpoints;
import com.hackhalo2.lib.tweek.utils.TweekUtils;

public class TwitchOAuthHandler implements IOAuthHandler, IOAuthRedirectHandler {
	
	private final String handleKey = "Twitch";
	
	private final String redirectKey = "oauth_authorize_twitch";
	
	private final String clientID;
	
	public TwitchOAuthHandler(String clientID) {
		this.clientID = clientID;
	}

	@Override
	public String getRedirectURI() {
		return OAuthManager.getRedirectAddress()+this.getRedirectKey();
	}
	
	@Override
	public OAuthRequest requestPermissions(String... scopes) {
		OAuthRequest request = new OAuthRequest(this.handleKey);
		request.addScopes(scopes);
		return request;
		
	}

	@Override
	public String getAuthenticationURI(OAuthRequest request) {
		return String.format("%s/oauth2/authorize?response_type=code&client_id=%s&redirect_uri=%s&scope=%s&state=%s&nonce=%s&force_verify=true",
				TwitchURLEndpoints.KRAKEN,
				this.clientID,
				this.getRedirectURI(),
				TwitchScope.formatSpaceSeperated(request.getScopes()),
				request.getState(),
				TweekUtils.generateNonce()
				);
	}

	@Override
	public String getHandlerKey() {
		return this.handleKey;
	}

	@Override
	public String getRedirectKey() {
		return this.redirectKey;
	}

	@Override
	public IOAuthToken handleRedirect(OAuthRequest request, Map<String, List<String>> queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

}
