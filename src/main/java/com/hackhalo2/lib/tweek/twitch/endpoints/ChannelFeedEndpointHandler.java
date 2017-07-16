package com.hackhalo2.lib.tweek.twitch.endpoints;

import java.util.Optional;

import org.apache.http.client.utils.URIBuilder;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hackhalo2.lib.tweek.exceptions.EndpointException;
import com.hackhalo2.lib.tweek.exceptions.InvalidNullException;
import com.hackhalo2.lib.tweek.exceptions.InvalidOAuthTokenException;
import com.hackhalo2.lib.tweek.exceptions.MissingScopeException;
import com.hackhalo2.lib.tweek.oauth.IOAuthToken;
import com.hackhalo2.lib.tweek.twitch.TwitchScope;
import com.hackhalo2.lib.tweek.twitch.jsonobjects.TwitchChannelFeedPost;
import com.hackhalo2.lib.tweek.twitch.jsonobjects.TwitchChannelFeedPostList;
import com.hackhalo2.lib.tweek.utils.TokenUtils;
import com.hackhalo2.lib.tweek.utils.TweekUtils;

public final class ChannelFeedEndpointHandler extends TwitchEndpointHandler {

	protected ChannelFeedEndpointHandler(String clientID) {
		super(clientID);
	}

	public Optional<TwitchChannelFeedPostList> getMultipleFeedPosts(long channelID, @Nullable IOAuthToken token)
			throws EndpointException {
		return this.getMultipleFeedPosts(channelID, token, 10, null, 5);
	}

	public Optional<TwitchChannelFeedPostList> getMultipleFeedPosts(long channelID, @Nullable IOAuthToken token, @Nullable String cursor)
			throws EndpointException {
		return this.getMultipleFeedPosts(channelID, token, 10, cursor, 5);
	}

	public Optional<TwitchChannelFeedPostList> getMultipleFeedPosts(long channelID, @Nullable IOAuthToken token, long limit, @Nullable String cursor, long comments)
			throws EndpointException {
		if(token != null && !this.isTwitchToken(token)) throw new InvalidOAuthTokenException("Incorrect OAuth Token passed!");

		String requestURL = null;
		TwitchChannelFeedPostList response = null;

		try {
			URIBuilder builder = new URIBuilder(String.format(TwitchURLEndpoints.CHANFEED, channelID));
			if(limit != 10 && limit > -1 && limit < 101) builder.addParameter("limit", ""+limit);
			if(cursor != null) builder.addParameter("cursor", cursor);
			if(comments != 5 && comments > -1 && comments < 6) builder.addParameter("comments", ""+comments);
			requestURL = builder.build().toString();
			
			if(requestURL == null) return Optional.empty();

			response = this.packets.GET.sendRequest(requestURL, TwitchChannelFeedPostList.class, token);
		} catch (Exception e) {
			throw new EndpointException("Encountered an error inside ChannelFeedEndpoint.getMultipleFeedPosts()", e);
		}

		return Optional.ofNullable(response);
	}

	public Optional<TwitchChannelFeedPost> getFeedPost(long channelID, @NonNull String postID, @Nullable IOAuthToken token)
			throws EndpointException {
		return this.getFeedPost(channelID, postID, token, 5);
	}

	public Optional<TwitchChannelFeedPost> getFeedPost(long channelID, @NonNull String postID, @Nullable IOAuthToken token, long comments)
			throws EndpointException {
		if(postID == null) throw new InvalidNullException("PostID cannot be null for ChannelFeedEndpointHandler.getFeedPost()!");
		if(token != null && !this.isTwitchToken(token)) throw new InvalidOAuthTokenException("Incorrect OAuth Token passed!");

		String requestURL = null;
		TwitchChannelFeedPost result = null;

		try {
			URIBuilder builder = new URIBuilder(String.format(TwitchURLEndpoints.CHANFEED_POST, channelID, postID));
			if(comments != 5 && comments > -1 && comments < 6) builder.addParameter("comments", ""+comments);
			requestURL = builder.build().toString();
			
			if(requestURL == null) return Optional.empty();

			result = this.packets.GET.sendRequest(requestURL, TwitchChannelFeedPost.class, token);
		} catch (Exception e) {
			throw new EndpointException("Encountered an error inside ChannelFeedEndpoint.getFeedPost()", e);
		}
		return Optional.ofNullable(result);
	}

	public Optional<TwitchChannelFeedPost> createFeedPost(long channelID, @NonNull String content, @NonNull IOAuthToken token)
			throws MissingScopeException, EndpointException {
		return this.createFeedPost(channelID, content, token, false);
	}

	public Optional<TwitchChannelFeedPost> createFeedPost(long channelID, @NonNull String content, @NonNull IOAuthToken token, boolean share)
			throws MissingScopeException, EndpointException {
		if(content == null) throw new InvalidNullException("Content cannot be null for ChannelFeedEndpointHandler.createFeedPost()!");
		if(token == null) throw new InvalidNullException("OAuthToken cannot be null for ChannelFeedEndpointHandler.createFeedPost()!");
		if(!this.isTwitchToken(token)) throw new InvalidOAuthTokenException("Incorrect OAuth Token passed!");

		if(!token.hasScope(TwitchScope.CHANNEL_FEED_EDIT.name())) {
			throw new MissingScopeException("Scope '"+TwitchScope.CHANNEL_FEED_EDIT.name()+"' is missing from AuthToken! (Available scopes: "
					+TokenUtils.formatHumanReadable(token.getScopes())+")", TwitchScope.CHANNEL_FEED_EDIT.name());
		}

		String requestURL = null;
		TwitchChannelFeedPost result = null;
		ObjectNode json = null;

		try {
			URIBuilder builder = new URIBuilder(String.format(TwitchURLEndpoints.CHANFEED, channelID));
			if(share) builder.addParameter("share", ""+share);
			requestURL = builder.build().toString();
			
			if(requestURL == null) return Optional.empty();
			
			json = TweekUtils.OBJMAP.createObjectNode();
			json.put("content", content);

			result = this.packets.POST.sendJSONRequest(requestURL, json, TwitchChannelFeedPost.class, token);
		} catch (Exception e) {
			throw new EndpointException("Encountered an error inside ChannelFeedEndpoint.createFeedPost()", e);
		}

		return Optional.ofNullable(result);
	}

}
