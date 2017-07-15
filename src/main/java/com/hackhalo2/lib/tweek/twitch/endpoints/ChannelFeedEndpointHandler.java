package com.hackhalo2.lib.tweek.twitch.endpoints;

import java.util.Optional;

import org.apache.http.client.utils.URIBuilder;
import org.eclipse.jdt.annotation.Nullable;

import com.hackhalo2.lib.tweek.oauth.IOAuthToken;
import com.hackhalo2.lib.tweek.twitch.jsonobjects.TwitchChannelFeedPostList;

public final class ChannelFeedEndpointHandler extends TwitchEndpointHandler {

	protected ChannelFeedEndpointHandler(String clientID) {
		super(clientID);
	}
	
	public Optional<TwitchChannelFeedPostList> getMultipleFeedPosts(long channelID, @Nullable IOAuthToken token) {
		return this.getMultipleFeedPosts(channelID, token, 10, null, 5);
	}
	
	public Optional<TwitchChannelFeedPostList> getMultipleFeedPosts(long channelID, @Nullable IOAuthToken token, @Nullable String cursor) {
		return this.getMultipleFeedPosts(channelID, token, 10, cursor, 5);
	}
	
	public Optional<TwitchChannelFeedPostList> getMultipleFeedPosts(long channelID, @Nullable IOAuthToken token, long limit, @Nullable String cursor, long comments) {
		String requestURL = null;
		
		try {
			URIBuilder builder = new URIBuilder(String.format(TwitchURLEndpoints.CHANFEED, channelID));
			if(limit != 10 && limit > -1 && limit < 101) builder.addParameter("limit", ""+limit);
			if(cursor != null) builder.addParameter("cursor", cursor);
			if(comments != 5 && comments > -1 && comments < 6) builder.addParameter("comments", ""+comments);
			requestURL = builder.build().toString();
		} catch (Exception e) {
			//TODO: Log
			return Optional.empty();
		}
		
		if(requestURL == null) return Optional.empty();
		
		TwitchChannelFeedPostList response = this.packets.GET.sendRequest(requestURL, TwitchChannelFeedPostList.class, token);
		return Optional.ofNullable(response);
	}

}
