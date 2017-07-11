package com.hackhalo2.lib.tweek.twitch;

/**
 * Static class that contains the URLs of various Twitch API Endpoints
 * 
 * @author HACKhalo2
 */
public final class TwitchEndpoints {
	
	/**
	 * The version of the Kraken API to use
	 */
	public static final int KRAKEN_VERSION = 5;
	
	/**
	 * The version of the PubSub API to use
	 */
	public static final int PUBSUB_VERSION = 1;

	/**
	 * The main Twitch API endpoint
	 */
	public static final String KRAKEN = "https://api.twitch.tv/kraken/";
	
	/* ------------------------------------------------------------ *
	 * ---------------------- Channel Feed ------------------------ *
	 * ------------------------------------------------------------ */
	
	/**
	 * The formatted Endpoint for accessing a Channels Feed<br />
	 * Used to POST a new Feed, or to GET every post on a Channels Feed<br />
	 * Required input order: Channel_ID
	 */
	public static final String CHANFEED = KRAKEN + "feed/%d/posts";
	
	/**
	 * The formatted Enpoint for a single Post on a Channels Feed<br />
	 * Used to DELETE a Post, to GET a Post, or to POST a Post<br />
	 * <b>Scope <i>CHANNEL_FEED_EDIT</i> is needed for POST and DELETE requests</b><br />
	 * Required input order: Channel_ID, Post_ID
	 */
	public static final String CHANFEED_POST = CHANFEED + "/%d";
	
	/**
	 * The formatted Endpoint for Reacting to a single Channels Feed Post<br />
	 * Used to POST a Reaction, or to DELETE a Reaction<br />
	 * <b>Scope <i>CHANNEL_FEED_EDIT</i> is needed</b><br />
	 * Required input order: Channel_ID, Post_ID, Emote_ID
	 */
	public static final String CHANFEED_POST_REACT = CHANFEED_POST + "/reactions?emote_id=%d";
	
	/**
	 * The formatted Endpoint for Comments on a single Channels Feed Post<br />
	 * Used to POST a new Comment, or to GET every comment to a Post<br />
	 * <b>Scope <i>CHANNEL_FEED_EDIT</i> is needed for POST requests</b><br />
	 * Required input order: Channel_ID, Post_ID
	 */
	public static final String CHANFEED_COMMENTS = CHANFEED_POST + "/comments";
	
	/**
	 * The formatted Endpoint for a single Comment on a Channels Feed Post<br />
	 * Used to DELETE a Comment<br />
	 * <b>Scope <i>CHANNEL_FEED_EDIT</i> is needed</b><br />
	 * Required input order: Channel_ID, Post_ID, Comment_ID
	 */
	public static final String CHANFEED_COMMENT = CHANFEED_COMMENTS + "/%d";
	
	/**
	 * The formatted Endpoint for Reacting to a single Comment on a Channels Feed Post<br />
	 * Used to POST a Reaction, or to DELETE a Reaction<br />
	 * <b>Scope <i>CHANNEL_FEED_EDIT</i> is needed</b><br />
	 * Required input order: Channel_ID, Post_ID, Comment_ID, Emote_ID
	 */
	public static final String CHANFEED_COMMENT_REACT = CHANFEED_COMMENT + "/reactions?emote_id=%d";
	
	/* ------------------------------------------------------------ *
	 * ------------------------ Channels -------------------------- *
	 * ------------------------------------------------------------ */
	
	/**
	 * The Channel Endpoint for a channel specified by an OAuth token in the request header<br />
	 * Used to GET a single Channel based on the OAuth token provided in the header<br />
	 * <b>Scope <i>CHANNEL_READ</i> is needed</b><br />
	 */
	public static final String OAUTH_CHANNEL = KRAKEN + "channel";
	
	/**
	 * The formatted Endpoint for a single Channel<br />
	 * Used to GET the status of a Channel, or to PUT new info for a Channel<br />
	 * <b>Scope <i>CHANNEL_EDITOR</i> is needed for PUT requests</b><br />
	 * Required input order: Channel_ID
	 */
	public static final String CHANNEL = OAUTH_CHANNEL + "s/%d";
	
	/**
	 * 
	 */
	public static final String CHANNEL_EDITORS = CHANNEL + "/editors";
	
	/**
	 * 
	 */
	public static final String CHANNEL_FOLLOWERS = CHANNEL + "/follows";
	
	/**
	 * 
	 */
	public static final String CHANNEL_TEAMS = CHANNEL + "/teams";
	
	/**
	 * 
	 */
	public static final String CHANNEL_SUBS = CHANNEL + "/subscriptions";
	
	/**
	 * 
	 */
	public static final String CHANNEL_CHECKSUB = CHANNEL_SUBS + "/%d";
	
	/**
	 * 
	 */
	public static final String CHANNEL_VIDEOS = CHANNEL + "/videos";
	
	/**
	 * 
	 */
	public static final String CHANNEL_COMMERCIAL = CHANNEL + "/commercial";
	
	/**
	 * 
	 */
	public static final String CHANNEL_RESET_KEY = CHANNEL + "/stream_key";
	
	/**
	 * 
	 */
	public static final String CHANNEL_COMMUNITY = CHANNEL + "/community";
	
	/* ------------------------------------------------------------ *
	 * --------------------------- Chat --------------------------- *
	 * ------------------------------------------------------------ */
	
	/**
	 * 
	 */
	public static final String CHAT_BASE = KRAKEN + "chat";
	
	/**
	 * 
	 */
	public static final String CHAT_BADGES = CHAT_BASE + "/%d/badges";
	
	/* ------------------------------------------------------------ *
	 * -------------------------- Clips --------------------------- *
	 * ------------------------------------------------------------ */
	
	/**
	 * 
	 */
	public static final String CLIP = KRAKEN + "clips/%s";
	
	
	
	/**
	 * The main Twitch PubSub Websocket API endpoint
	 */
	public static final String PUBSUB = "wss://pubsub-edge.twitch.tv";
	
	/**
	 * 
	 */
	public static final String PUBSUB_CHANNEL_BITS = "channel-bits-events-v%d.%d";
	
	/**
	 * 
	 */
	public static final String PUBSUB_CHANNEL_SUB = "channel-subscribe-events-v%d.%d";
	
	/**
	 * 
	 */
	public static final String PUBSUB_CHANNEL_COMMERCE = "channel-commerce-events-v%d.%d";
	
	/**
	 * 
	 */
	public static final String PUBSUB_USER_WHISPER = "whispers.%d";
	
	/**
	 * The main Twitch Messaging Interface (TMI) API Endpoint
	 */
	public static final String TMI = "http://tmi.twitch.tv";
}
