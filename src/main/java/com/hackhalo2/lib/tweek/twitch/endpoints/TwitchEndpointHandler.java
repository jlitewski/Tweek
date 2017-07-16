package com.hackhalo2.lib.tweek.twitch.endpoints;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.annotation.NonNull;

import com.hackhalo2.lib.tweek.exceptions.EndpointException;
import com.hackhalo2.lib.tweek.oauth.IOAuthToken;
import com.hackhalo2.lib.tweek.twitch.jsonobjects.TwitchUser;
import com.hackhalo2.lib.tweek.twitch.jsonobjects.TwitchUserList;
import com.hackhalo2.lib.tweek.twitch.oauth.TwitchOAuthToken;
import com.hackhalo2.lib.tweek.utils.PacketBuilder;
import com.hackhalo2.lib.tweek.utils.TweekUtils;

class TwitchEndpointHandler {

	private String clientID;
	protected PacketBuilder packets;

	protected TwitchEndpointHandler(String clientID) {
		this.clientID = clientID;
		this.packets = new PacketBuilder(clientID);
	}

	protected String getClientID() {
		return this.clientID;
	}

	protected boolean isTwitchToken(IOAuthToken token) {
		return (token instanceof TwitchOAuthToken);
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
	 * Gets the Twitch User from the supplied Username<br />
	 * <i>Note: This uses a Twitch Endpoint that was set up to convert V3 usernames to
	 * V5 User IDs. It may not be available forever.</i>
	 * @param username The username to get the TwitchUser for
	 * @return The TwitchUser Object from the username given
	 */
	public Optional<TwitchUser> getUserfromUsername(@NonNull String username)
			throws EndpointException {
		String requestURL = String.format(TwitchURLEndpoints.USERS_GET, username);
		TwitchUserList response = null;
		try {
			response = this.packets.GET.sendRequest(requestURL, TwitchUserList.class);
			if(response._total > 1) {
				//TODO: Log that there were more than one result
			} else if(response._total < 1) {
				//TODO: no users exist, log
				return Optional.empty();
			}
		} catch (Exception e) {
			throw new EndpointException("There was a REST API issue!", e);
		}
		
		return response.getUserAtIndex(0);
	}

	/**
	 * Gets a list of Twitch Users from an array of usernames, up to 100 usernames. The rest are discarded.<br />
	 * <i>Note: This uses a Twitch Endpoint that was set up to convert V3 usernames to
	 * V5 User IDs. It may not be available forever.</i>
	 * @param usernames The array of usernames to get
	 * @return The TwitchUserList Object for the array of usernames
	 * @throws EndpointException 
	 */
	public Optional<TwitchUserList> getUsersfromUsernames(@NonNull String... usernames)
			throws EndpointException {
		String users = null;
		if(usernames.length > 100) {
			StringBuilder builder = new StringBuilder();
			for(int i = 0; i < 100; i++) {
				builder.append(usernames[i]);
				if(i < 99) builder.append(",");
			}
			users = builder.toString();
		} else users = StringUtils.join(usernames, ",");

		String requestURL = String.format(TwitchURLEndpoints.USERS_GET, users);
		TwitchUserList response = null;
		try {
			response = this.packets.GET.sendRequest(requestURL, TwitchUserList.class);
		} catch (Exception e) {
			throw new EndpointException("There was a REST API issue!", e);
		}

		return Optional.ofNullable(response);
	}

	protected Optional<Long> getChannelIDforUsername(@NonNull String username) {
		return null; //TODO
	}

	protected Optional<Long> getChannelIDforUserID(long userid) {
		return null; //TODO
	}

}
