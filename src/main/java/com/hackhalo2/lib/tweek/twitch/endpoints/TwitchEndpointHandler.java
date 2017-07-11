package com.hackhalo2.lib.tweek.twitch.endpoints;

import java.util.List;
import java.util.Optional;

import org.eclipse.jdt.annotation.NonNull;

import com.hackhalo2.lib.tweek.twitch.TweekUtils;
import com.hackhalo2.lib.tweek.twitch.TwitchScope;

class TwitchEndpointHandler {
	
	protected TwitchEndpointHandler() {
		
	}
	
	/**
	 * Checks the Scopes needed for the API request against the Applications Scopes
	 * @param current The current scopes the Application has
	 * @param required The required scope the API call needs
	 * @return true if the Application has the requires scopes to make the request, false otherwise
	 */
	protected boolean checkScopes(@NonNull List<TwitchScope> current, @NonNull List<TwitchScope> required) {
		for(TwitchScope check : required) {
			if(!current.contains(check)) {
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Helper method to cache API responses for possible reuse. These cached responses last for 5 minutes before being purged from the cache.
	 * @param request The request used to get the response
	 * @param response The response gotten from the request
	 */
	protected void cacheResponse(@NonNull String request, @NonNull Object response) {
		TweekUtils.restCache.put(request, response);
	}
	
	/**
	 * Helper method to get a previously cached API response from a given request. This will also reset the cache timer for the given request.
	 * @param request The API request
	 * @return The cached response
	 */
	protected Object getCachedResponse(@NonNull String request) {
		Object response = TweekUtils.restCache.remove(request);
		TweekUtils.restCache.put(request, response);
		return TweekUtils.restCache.get(request);
	}
	
	/**
	 * Helper method to check to see if the given request is cached
	 * @param request The request to check
	 * @return true if it's in the cache, false otherwise
	 */
	protected boolean isResponsedCached(@NonNull String request) {
		return TweekUtils.restCache.containsKey(request);
	}
	
	/**
	 * Helper Method to invalidate a cached response to the given request
	 * @param request The request to invalidate
	 */
	protected void invalidateCachedResponse(@NonNull String request) {
		TweekUtils.restCache.remove(request);
	}
	
	/**
	 * Helper method to get the User ID from a username
	 * @param username The username to get the ID for
	 * @return 
	 */
	protected Optional<Long> getUserIDfromUsername(@NonNull String username) {
		return null; //TODO
	}
	
	protected Optional<Long> getChannelIDforUsername(@NonNull String username) {
		return null; //TODO
	}
	
	protected Optional<Long> getChannelIDforUserID(long userid) {
		return null; //TODO
	}
	
}
