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
import com.hackhalo2.lib.tweek.twitch.jsonobjects.TwitchComment;
import com.hackhalo2.lib.tweek.twitch.jsonobjects.TwitchCommentList;
import com.hackhalo2.lib.tweek.twitch.jsonobjects.TwitchDeletionResponse;
import com.hackhalo2.lib.tweek.twitch.jsonobjects.TwitchReactCreation;
import com.hackhalo2.lib.tweek.utils.TokenUtils;
import com.hackhalo2.lib.tweek.utils.TweekUtils;

public final class ChannelFeedEndpointHandler extends TwitchEndpointHandler {

	protected ChannelFeedEndpointHandler(String clientID) {
		super(clientID);
	}

	/**
	 * Gets posts from a specified channel feed. Uses defaults for all optional parameters<br /><br />
	 * <b>Authentication</b><br />
	 * Optional scope: <i>any scope</i><br /><br />
	 * <i>If authentication is provided, the <b><code>user_ids</code></b> array in the response body contains the requesting user’s ID, if they have reacted to a post.</i>
	 * @param channelID The <b><code>_id</code></b> field from {@link ChannelEndpointHandler#getChannel()}
	 * @param token A Twitch OAuth2 Token Object to use for Authorization. Can be <code>null</code>
	 * @return a {@link TwitchChannelFeedPostList} Object, wrapped in a {@link Optional} for <code>null</code> saftey
	 * @throws EndpointException if there was an error during any part of the Methods execution
	 */
	public Optional<TwitchChannelFeedPostList> getMultipleFeedPosts(long channelID, @Nullable IOAuthToken token)
			throws EndpointException {
		return this.getMultipleFeedPosts(channelID, token, 10, null, 5);
	}

	/**
	 * Gets posts from a specified channel feed at the supplied cursor. Uses defaults for all optional parameters<br /><br />
	 * <b>Authentication</b><br />
	 * Optional scope: <i>any scope</i><br /><br />
	 * <i>If authentication is provided, the <b><code>user_ids</code></b> array in the response body contains the requesting user’s ID, if they have reacted to a post.</i>
	 * @param channelID The <b><code>_id</code></b> field from {@link ChannelEndpointHandler#getChannel()}
	 * @param token A Twitch OAuth2 Token Object to use for Authorization. Can be <code>null</code>
	 * @param cursor The <b><code>_cursor</code></b> field from a <b>previously returned</b> {@link TwitchChannelFeedPostList} Object. 
	 * Tells the server where to start fetching the next set of results in a multi-page response. Can be <code>null</code>
	 * @return a {@link TwitchChannelFeedPostList} Object, wrapped in a {@link Optional} for <code>null</code> saftey
	 * @throws EndpointException if there was an error during any part of the Methods execution
	 */
	public Optional<TwitchChannelFeedPostList> getMultipleFeedPosts(long channelID, @Nullable IOAuthToken token, @Nullable String cursor)
			throws EndpointException {
		return this.getMultipleFeedPosts(channelID, token, 10, cursor, 5);
	}

	/**
	 * Gets posts from a specified channel feed at the supplied cursor. Uses the default for the <code>comments</code> parameter<br /><br />
	 * <b>Authentication</b><br />
	 * Optional scope: <i>any scope</i><br /><br />
	 * <i>If authentication is provided, the <b><code>user_ids</code></b> array in the response body contains the requesting user’s ID, if they have reacted to a post.</i>
	 * @param channelID The <b><code>_id</code></b> field from {@link ChannelEndpointHandler#getChannel()}
	 * @param token A Twitch OAuth2 Token Object to use for Authorization. Can be <code>null</code>
	 * @param limit Maximum number of most-recent objects to return. Default: 10, Maximum: 100
	 * @param cursor The <b><code>_cursor</code></b> field from a <b>previously returned</b> {@link TwitchChannelFeedPostList} Object. 
	 * Tells the server where to start fetching the next set of results in a multi-page response. Can be <code>null</code>
	 * @return a {@link TwitchChannelFeedPostList} Object, wrapped in a {@link Optional} for <code>null</code> saftey
	 * @throws EndpointException if there was an error during any part of the Methods execution
	 */
	public Optional<TwitchChannelFeedPostList> getMultipleFeedPosts(long channelID, @Nullable IOAuthToken token, long limit, @Nullable String cursor)
			throws EndpointException {
		return this.getMultipleFeedPosts(channelID, token, limit, cursor, 5);
	}

	/**
	 * Gets posts from a specified channel feed at the supplied cursor. Uses the default for the <code>limit</code> parameter<br /><br />
	 * <b>Authentication</b><br />
	 * Optional scope: <i>any scope</i><br /><br />
	 * <i>If authentication is provided, the <b><code>user_ids</code></b> array in the response body contains the requesting user’s ID, if they have reacted to a post.</i>
	 * @param channelID The <b><code>_id</code></b> field from {@link ChannelEndpointHandler#getChannel()}
	 * @param token A Twitch OAuth2 Token Object to use for Authorization. Can be <code>null</code>
	 * @param cursor The <b><code>_cursor</code></b> field from a <b>previously returned</b> {@link TwitchChannelFeedPostList} Object. 
	 * Tells the server where to start fetching the next set of results in a multi-page response. Can be <code>null</code>
	 * @param comments Specifies the number of most-recent comments on posts that are included in the response. Default: 5. Maximum: 5
	 * @return a {@link TwitchChannelFeedPostList} Object, wrapped in a {@link Optional} for <code>null</code> saftey
	 * @throws EndpointException if there was an error during any part of the Methods execution
	 */
	public Optional<TwitchChannelFeedPostList> getMultipleFeedPosts(long channelID, @Nullable IOAuthToken token, @Nullable String cursor, long comments)
			throws EndpointException {
		return this.getMultipleFeedPosts(channelID, token, 10, cursor, comments);
	}

	/**
	 * Gets posts from a specified channel feed. Use the default for the <code>limit</code> parameter<br /><br />
	 * <b>Authentication</b><br />
	 * Optional scope: <i>any scope</i><br /><br />
	 * <i>If authentication is provided, the <b><code>user_ids</code></b> array in the response body contains the requesting user’s ID, if they have reacted to a post.</i>
	 * @param channelID The <b><code>_id</code></b> field from {@link ChannelEndpointHandler#getChannel()}
	 * @param token A Twitch OAuth2 Token Object to use for Authorization. Can be <code>null</code>
	 * @param comments Specifies the number of most-recent comments on posts that are included in the response. Default: 5. Maximum: 5
	 * @return a {@link TwitchChannelFeedPostList} Object, wrapped in a {@link Optional} for <code>null</code> saftey
	 * @throws EndpointException if there was an error during any part of the Methods execution
	 */
	public Optional<TwitchChannelFeedPostList> getMultipleFeedPosts(long channelID, @Nullable IOAuthToken token, long comments)
			throws EndpointException {
		return this.getMultipleFeedPosts(channelID, token, 10, null, comments);
	}

	/**
	 * Gets posts from a specified channel feed.<br /><br />
	 * <b>Authentication</b><br />
	 * Optional scope: <i>any scope</i><br /><br />
	 * <i>If authentication is provided, the <b><code>user_ids</code></b> array in the response body contains the requesting user’s ID, if they have reacted to a post.</i>
	 * @param channelID The <b><code>_id</code></b> field from {@link ChannelEndpointHandler#getChannel()}
	 * @param token A Twitch OAuth2 Token Object to use for Authorization. Can be <code>null</code>
	 * @param limit Maximum number of most-recent objects to return. Default: 10, Maximum: 100
	 * @param comments Specifies the number of most-recent comments on posts that are included in the response. Default: 5. Maximum: 5
	 * @return a {@link TwitchChannelFeedPostList} Object, wrapped in a {@link Optional} for <code>null</code> saftey
	 * @throws EndpointException if there was an error during any part of the Methods execution
	 */
	public Optional<TwitchChannelFeedPostList> getMultipleFeedPosts(long channelID, @Nullable IOAuthToken token, long limit, long comments)
			throws EndpointException {
		return this.getMultipleFeedPosts(channelID, token, limit, null, comments);
	}

	/**
	 * Gets posts from a specified channel feed at the supplied cursor.<br /><br />
	 * <b>Authentication</b><br />
	 * Optional scope: <i>any scope</i><br /><br />
	 * <i>If authentication is provided, the <b><code>user_ids</code></b> array in the response body contains the requesting user’s ID, if they have reacted to a post.</i>
	 * @param channelID The <b><code>_id</code></b> field from {@link ChannelEndpointHandler#getChannel()}
	 * @param token A Twitch OAuth2 Token Object to use for Authorization. Can be <code>null</code>
	 * @param limit Maximum number of most-recent objects to return. Default: 10, Maximum: 100
	 * @param cursor The <b><code>_cursor</code></b> field from a <b>previously returned</b> {@link TwitchChannelFeedPostList} Object. 
	 * Tells the server where to start fetching the next set of results in a multi-page response. Can be <code>null</code>
	 * @param comments Specifies the number of most-recent comments on posts that are included in the response. Default: 5. Maximum: 5
	 * @return a {@link TwitchChannelFeedPostList} Object, wrapped in a {@link Optional} for <code>null</code> saftey
	 * @throws EndpointException if there was an error during any part of the Methods execution
	 */
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

	public Optional<TwitchChannelFeedPost> deleteFeedPost(long channelID, @NonNull String postID, @NonNull IOAuthToken token)
			throws MissingScopeException, EndpointException {
		if(postID == null) throw new InvalidNullException("PostID cannot be null for ChannelFeedEndpointHandler.deleteFeedPost()!");
		if(token == null) throw new InvalidNullException("OAuthToken cannot be null for ChannelFeedEndpointHandler.deleteFeedPost()!");
		if(!this.isTwitchToken(token)) throw new InvalidOAuthTokenException("Incorrect OAuth Token passed!");

		if(!token.hasScope(TwitchScope.CHANNEL_FEED_EDIT.name())) {
			throw new MissingScopeException("Scope '"+TwitchScope.CHANNEL_FEED_EDIT.name()+"' is missing from AuthToken! (Available scopes: "
					+TokenUtils.formatHumanReadable(token.getScopes())+")", TwitchScope.CHANNEL_FEED_EDIT.name());
		}

		String requestURL = null;
		TwitchChannelFeedPost result = null;

		try {
			URIBuilder builder = new URIBuilder(String.format(TwitchURLEndpoints.CHANFEED_POST, channelID, postID));
			requestURL = builder.build().toString();

			if(requestURL == null) return Optional.empty();

			result = this.packets.DELETE.sendRequest(requestURL, TwitchChannelFeedPost.class, token);
		} catch (Exception e) {
			throw new EndpointException("Encountered an error inside ChannelFeedEndpoint.deleteFeedPost()", e);
		}

		return Optional.ofNullable(result);
	}

	public Optional<TwitchReactCreation> createReactionOnFeedPost(long channelID, @NonNull String postID, @Nullable String emoteID, @NonNull IOAuthToken token)
			throws MissingScopeException, EndpointException {
		if(postID == null) throw new InvalidNullException("PostID cannot be null for ChannelFeedEndpointHandler.createReactionOnFeedPost()!");
		if(token == null) throw new InvalidNullException("OAuthToken cannot be null for ChannelFeedEndpointHandler.createReactionOnFeedPost()!");
		if(!this.isTwitchToken(token)) throw new InvalidOAuthTokenException("Incorrect OAuth Token passed!");

		if(!token.hasScope(TwitchScope.CHANNEL_FEED_EDIT.name())) {
			throw new MissingScopeException("Scope '"+TwitchScope.CHANNEL_FEED_EDIT.name()+"' is missing from AuthToken! (Available scopes: "
					+TokenUtils.formatHumanReadable(token.getScopes())+")", TwitchScope.CHANNEL_FEED_EDIT.name());
		}

		String requestURL = null;
		TwitchReactCreation result = null;

		try {
			String emote = (emoteID == null? "endorse" : emoteID);
			URIBuilder builder = new URIBuilder(String.format(TwitchURLEndpoints.CHANFEED_POST_REACT, channelID, postID, emote));
			requestURL = builder.build().toString();

			if(requestURL == null) return Optional.empty();

			result = this.packets.POST.sendRequest(requestURL, TwitchReactCreation.class, token);
		} catch (Exception e) {
			throw new EndpointException("Encountered an error inside ChannelFeedEndpoint.createReactionOnFeedPost()", e);
		}

		return Optional.ofNullable(result);
	}

	public Optional<TwitchDeletionResponse> deleteReactionOnFeedPost(long channelID, @NonNull String postID, @Nullable String emoteID, @NonNull IOAuthToken token)
			throws MissingScopeException, EndpointException {
		if(postID == null) throw new InvalidNullException("PostID cannot be null for ChannelFeedEndpointHandler.deleteReactionOnFeedPost()!");
		if(token == null) throw new InvalidNullException("OAuthToken cannot be null for ChannelFeedEndpointHandler.deleteReactionOnFeedPost()!");
		if(!this.isTwitchToken(token)) throw new InvalidOAuthTokenException("Incorrect OAuth Token passed!");

		if(!token.hasScope(TwitchScope.CHANNEL_FEED_EDIT.name())) {
			throw new MissingScopeException("Scope '"+TwitchScope.CHANNEL_FEED_EDIT.name()+"' is missing from AuthToken! (Available scopes: "
					+TokenUtils.formatHumanReadable(token.getScopes())+")", TwitchScope.CHANNEL_FEED_EDIT.name());
		}

		String requestURL = null;
		TwitchDeletionResponse result = null;

		try {
			String emote = (emoteID == null? "endorse" : emoteID);
			URIBuilder builder = new URIBuilder(String.format(TwitchURLEndpoints.CHANFEED_POST_REACT, channelID, postID, emote));
			requestURL = builder.build().toString();

			if(requestURL == null) return Optional.empty();

			result = this.packets.DELETE.sendRequest(requestURL, TwitchDeletionResponse.class, token);
		} catch (Exception e) {
			throw new EndpointException("Encountered an error inside ChannelFeedEndpoint.deleteReactionOnFeedPost()", e);
		}

		return Optional.ofNullable(result);
	}

	public Optional<TwitchCommentList> getFeedComments(long channelID, @NonNull String postID, @Nullable IOAuthToken token)
			throws EndpointException {
		return this.getFeedComments(channelID, postID, null, 10, token);
	}

	public Optional<TwitchCommentList> getFeedComments(long channelID, @NonNull String postID, @Nullable String cursor, @Nullable IOAuthToken token)
			throws EndpointException {
		return this.getFeedComments(channelID, postID, cursor, 10, token);
	}

	public Optional<TwitchCommentList> getFeedComments(long channelID, @NonNull String postID, @Nullable String cursor, long limit, @Nullable IOAuthToken token)
			throws EndpointException {
		if(token != null && !this.isTwitchToken(token)) throw new InvalidOAuthTokenException("Incorrect OAuth Token passed!");
		if(postID == null) throw new InvalidNullException("PostID cannot be null for ChannelFeedEndpointHandler.getFeedComments()!");

		String requestURL = null;
		TwitchCommentList response = null;

		try {
			URIBuilder builder = new URIBuilder(String.format(TwitchURLEndpoints.CHANFEED_COMMENTS, channelID, postID));
			if(limit != 10 && limit > -1 && limit < 101) builder.addParameter("limit", ""+limit);
			if(cursor != null) builder.addParameter("cursor", cursor);
			requestURL = builder.build().toString();

			if(requestURL == null) return Optional.empty();

			response = this.packets.GET.sendRequest(requestURL, TwitchCommentList.class, token);
		} catch (Exception e) {
			throw new EndpointException("Encountered an error inside ChannelFeedEndpoint.getFeedComments()", e);
		}

		return Optional.ofNullable(response);
	}

	public Optional<TwitchComment> createFeedComment(long channelID, @NonNull String postID, @NonNull String content, @NonNull IOAuthToken token)
			throws MissingScopeException, EndpointException {
		if(content == null) throw new InvalidNullException("Content cannot be null for ChannelFeedEndpointHandler.createFeedComment()!");
		if(postID == null) throw new InvalidNullException("PostID cannot be null for ChannelFeedEndpointHandler.createFeedComment()!");
		if(token == null) throw new InvalidNullException("OAuthToken cannot be null for ChannelFeedEndpointHandler.createFeedComment()!");
		if(!this.isTwitchToken(token)) throw new InvalidOAuthTokenException("Incorrect OAuth Token passed!");

		if(!token.hasScope(TwitchScope.CHANNEL_FEED_EDIT.name())) {
			throw new MissingScopeException("Scope '"+TwitchScope.CHANNEL_FEED_EDIT.name()+"' is missing from AuthToken! (Available scopes: "
					+TokenUtils.formatHumanReadable(token.getScopes())+")", TwitchScope.CHANNEL_FEED_EDIT.name());
		}

		String requestURL = null;
		TwitchComment result = null;
		ObjectNode json = null;

		try {
			URIBuilder builder = new URIBuilder(String.format(TwitchURLEndpoints.CHANFEED_COMMENTS, channelID, postID));
			requestURL = builder.build().toString();

			if(requestURL == null) return Optional.empty();

			json = TweekUtils.OBJMAP.createObjectNode();
			json.put("content", content);

			result = this.packets.POST.sendJSONRequest(requestURL, json, TwitchComment.class, token);
		} catch (Exception e) {
			throw new EndpointException("Encountered an error inside ChannelFeedEndpoint.createFeedComment()", e);
		}

		return Optional.ofNullable(result);
	}

	public Optional<TwitchComment> deleteFeedComment(long channelID, @NonNull String postID, @NonNull String commentID, @NonNull IOAuthToken token)
			throws MissingScopeException, EndpointException {
		if(postID == null) throw new InvalidNullException("PostID cannot be null for ChannelFeedEndpointHandler.deleteFeedComment()!");
		if(commentID == null) throw new InvalidNullException("CommentID cannot be null for ChannelFeedEndpointHandler.deleteFeedComment()!");
		if(token == null) throw new InvalidNullException("OAuthToken cannot be null for ChannelFeedEndpointHandler.deleteFeedComment()!");
		if(!this.isTwitchToken(token)) throw new InvalidOAuthTokenException("Incorrect OAuth Token passed!");

		if(!token.hasScope(TwitchScope.CHANNEL_FEED_EDIT.name())) {
			throw new MissingScopeException("Scope '"+TwitchScope.CHANNEL_FEED_EDIT.name()+"' is missing from AuthToken! (Available scopes: "
					+TokenUtils.formatHumanReadable(token.getScopes())+")", TwitchScope.CHANNEL_FEED_EDIT.name());
		}

		String requestURL = null;
		TwitchComment result = null;

		try {
			URIBuilder builder = new URIBuilder(String.format(TwitchURLEndpoints.CHANFEED_COMMENT, channelID, postID, commentID));
			requestURL = builder.build().toString();

			if(requestURL == null) return Optional.empty();

			result = this.packets.DELETE.sendRequest(requestURL, TwitchComment.class, token);
		} catch (Exception e) {
			throw new EndpointException("Encountered an error inside ChannelFeedEndpoint.deleteFeedComment()", e);
		}

		return Optional.ofNullable(result);
	}

	public Optional<TwitchReactCreation> createReactionOnFeedComment(long channelID, @NonNull String postID, @NonNull String commentID, @Nullable String emoteID, @NonNull IOAuthToken token)
			throws MissingScopeException, EndpointException {
		if(postID == null) throw new InvalidNullException("PostID cannot be null for ChannelFeedEndpointHandler.createReactionOnFeedComment()!");
		if(commentID == null) throw new InvalidNullException("CommentID cannot be null for ChannelFeedEndpointHandler.createReactionOnFeedComment()!");
		if(token == null) throw new InvalidNullException("OAuthToken cannot be null for ChannelFeedEndpointHandler.createReactionOnFeedComment()!");
		if(!this.isTwitchToken(token)) throw new InvalidOAuthTokenException("Incorrect OAuth Token passed!");

		if(!token.hasScope(TwitchScope.CHANNEL_FEED_EDIT.name())) {
			throw new MissingScopeException("Scope '"+TwitchScope.CHANNEL_FEED_EDIT.name()+"' is missing from AuthToken! (Available scopes: "
					+TokenUtils.formatHumanReadable(token.getScopes())+")", TwitchScope.CHANNEL_FEED_EDIT.name());
		}

		String requestURL = null;
		TwitchReactCreation result = null;

		try {
			String emote = (emoteID == null? "endorse" : emoteID);
			URIBuilder builder = new URIBuilder(String.format(TwitchURLEndpoints.CHANFEED_COMMENT_REACT, channelID, postID, commentID, emote));
			requestURL = builder.build().toString();

			if(requestURL == null) return Optional.empty();

			result = this.packets.POST.sendRequest(requestURL, TwitchReactCreation.class, token);
		} catch (Exception e) {
			throw new EndpointException("Encountered an error inside ChannelFeedEndpoint.createReactionOnFeedComment()", e);
		}

		return Optional.ofNullable(result);
	}

	public Optional<TwitchDeletionResponse> deleteReactionOnFeedComment(long channelID, @NonNull String postID, @NonNull String commentID, @Nullable String emoteID, @NonNull IOAuthToken token)
			throws MissingScopeException, EndpointException {
		if(postID == null) throw new InvalidNullException("PostID cannot be null for ChannelFeedEndpointHandler.deleteReactionOnFeedComment()!");
		if(commentID == null) throw new InvalidNullException("CommentID cannot be null for ChannelFeedEndpointHandler.deleteReactionOnFeedComment()!");
		if(token == null) throw new InvalidNullException("OAuthToken cannot be null for ChannelFeedEndpointHandler.deleteReactionOnFeedComment()!");
		if(!this.isTwitchToken(token)) throw new InvalidOAuthTokenException("Incorrect OAuth Token passed!");

		if(!token.hasScope(TwitchScope.CHANNEL_FEED_EDIT.name())) {
			throw new MissingScopeException("Scope '"+TwitchScope.CHANNEL_FEED_EDIT.name()+"' is missing from AuthToken! (Available scopes: "
					+TokenUtils.formatHumanReadable(token.getScopes())+")", TwitchScope.CHANNEL_FEED_EDIT.name());
		}

		String requestURL = null;
		TwitchDeletionResponse result = null;

		try {
			String emote = (emoteID == null? "endorse" : emoteID);
			URIBuilder builder = new URIBuilder(String.format(TwitchURLEndpoints.CHANFEED_COMMENT_REACT, channelID, postID, commentID, emote));
			requestURL = builder.build().toString();

			if(requestURL == null) return Optional.empty();

			result = this.packets.DELETE.sendRequest(requestURL, TwitchDeletionResponse.class, token);
		} catch (Exception e) {
			throw new EndpointException("Encountered an error inside ChannelFeedEndpoint.deleteReactionOnFeedComment()", e);
		}

		return Optional.ofNullable(result);
	}

}
