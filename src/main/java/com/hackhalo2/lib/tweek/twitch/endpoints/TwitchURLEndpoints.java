package com.hackhalo2.lib.tweek.twitch.endpoints;

/**
 * Static class that contains the URLs of various Twitch API Endpoints
 * 
 * @author HACKhalo2
 */
public final class TwitchURLEndpoints {
	
	private TwitchURLEndpoints() { }
	
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
	public static final String CHANFEED_POST = CHANFEED + "/%s";
	
	/**
	 * The formatted Endpoint for Reacting to a single Channels Feed Post<br />
	 * Used to POST a Reaction, or to DELETE a Reaction<br />
	 * <b>Scope <i>CHANNEL_FEED_EDIT</i> is needed</b><br />
	 * Required input order: Channel_ID, Post_ID, Emote_ID
	 */
	public static final String CHANFEED_POST_REACT = CHANFEED_POST + "/reactions?emote_id=%s";
	
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
	public static final String CHANFEED_COMMENT = CHANFEED_COMMENTS + "/%s";
	
	/**
	 * The formatted Endpoint for Reacting to a single Comment on a Channels Feed Post<br />
	 * Used to POST a Reaction, or to DELETE a Reaction<br />
	 * <b>Scope <i>CHANNEL_FEED_EDIT</i> is needed</b><br />
	 * Required input order: Channel_ID, Post_ID, Comment_ID, Emote_ID
	 */
	public static final String CHANFEED_COMMENT_REACT = CHANFEED_COMMENT + "/reactions?emote_id=%s";
	
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
	
	/**
	 * 
	 */
	public static final String CHAT_EMOTICONS = CHAT_BASE + "/emoticons";
	
	/**
	 * 
	 */
	public static final String CHAT_EMOTICON_IMAGES = CHAT_BASE + "/emoticon_images";
	
	/* ------------------------------------------------------------ *
	 * -------------------------- Clips --------------------------- *
	 * ------------------------------------------------------------ */
	
	/**
	 * 
	 */
	public static final String CLIPS = KRAKEN + "clips/"; 
	
	/**
	 * 
	 */
	public static final String CLIP = CLIPS + "%s";
	
	/**
	 * 
	 */
	public static final String CLIPS_TOP = CLIPS + "top";
	
	/**
	 * 
	 */
	public static final String CLIPS_FOLLOWED = CLIPS + "followed";
	
	/* ------------------------------------------------------------ *
	 * ----------------------- Collections ------------------------ *
	 * ------------------------------------------------------------ */
	
	/**
	 * 
	 */
	public static final String COLLECTIONS_BASE = KRAKEN + "collections/";
	
	/**
	 * 
	 */
	public static final String COLLECTION_METADATA = COLLECTIONS_BASE + "%s";
	
	/**
	 * 
	 */
	public static final String COLLECTION = COLLECTION_METADATA + "/items";
	
	/**
	 * 
	 */
	public static final String COLLECTIONS_CHANNEL = CHANNEL + "/collections";
	
	/**
	 * 
	 */
	public static final String COLLECTION_THUMBNAIL = COLLECTION_METADATA + "/thumbnail";
	
	/**
	 * 
	 */
	public static final String COLLECTION_ITEM = COLLECTION_METADATA + "/%s";
	
	/* ------------------------------------------------------------ *
	 * ----------------------- Communities ------------------------ *
	 * ------------------------------------------------------------ */
	
	/**
	 * 
	 */
	public static final String COMMUNITIES_BASE = KRAKEN + "communities";
	
	/**
	 * 
	 */
	public static final String COMMUNITY = COMMUNITIES_BASE + "/%s";
	
	/**
	 * 
	 */
	public static final String COMMUNITIES_TOP = COMMUNITIES_BASE + "/top";
	
	/**
	 * 
	 */
	public static final String COMMUNITY_BANS = COMMUNITY + "/bans";
	
	/**
	 * 
	 */
	public static final String COMMUNITY_BANNED_USER = COMMUNITY_BANS + "/%d";
	
	/**
	 * 
	 */
	public static final String COMMUNITY_AVATAR = COMMUNITY + "/images/avatar";
	
	/**
	 * 
	 */
	public static final String COMMUNITY_COVER = COMMUNITY + "/images/cover";
	
	/**
	 * 
	 */
	public static final String COMMUNITY_MODS = COMMUNITY + "/moderators";
	
	/**
	 * 
	 */
	public static final String COMMUNITY_MOD = COMMUNITY_MODS + "/%d";
	
	/**
	 * 
	 */
	public static final String COMMUNITY_PERMISSIONS = COMMUNITY + "/permissions";
	
	/**
	 * 
	 */
	public static final String COMMUNITY_REPORT = COMMUNITY + "/report_channel";
	
	/**
	 * 
	 */
	public static final String COMMUNITY_TIMEOUTS = COMMUNITY + "/timeouts";
	
	/**
	 * 
	 */
	public static final String COMMUNITY_TIMEOUT_USER = COMMUNITY_TIMEOUTS + "/%d";
	
	/* ------------------------------------------------------------ *
	 * -------------------------- Games --------------------------- *
	 * ------------------------------------------------------------ */
	
	/**
	 * 
	 */
	public static final String GAMES_BASE = KRAKEN + "games/";
	
	/**
	 * 
	 */
	public static final String GAMES_TOP = GAMES_BASE + "top";
	
	/* ------------------------------------------------------------ *
	 * -------------------------- Ingest -------------------------- *
	 * ------------------------------------------------------------ */
	
	/**
	 * 
	 */
	public static final String INGEST_BASE = KRAKEN + "ingests";
	
	/* ------------------------------------------------------------ *
	 * ------------------------- Search --------------------------- *
	 * ------------------------------------------------------------ */
	
	/**
	 * 
	 */
	public static final String SEARCH_BASE = KRAKEN + "search/";
	
	/**
	 * 
	 */
	public static final String SEARCH_CHANNELS = SEARCH_BASE + "channels?query=%s";
	
	/**
	 * 
	 */
	public static final String SEARCH_GAMES = SEARCH_BASE + "games?query=%s";
	
	/**
	 * 
	 */
	public static final String SEARCH_STREAMS = SEARCH_BASE + "streams?query=%s";
	
	/* ------------------------------------------------------------ *
	 * ------------------------ Streams --------------------------- *
	 * ------------------------------------------------------------ */
	
	/**
	 * 
	 */
	public static final String STREAMS_BASE = KRAKEN + "streams/";
	
	/**
	 * 
	 */
	public static final String STREAMS_USER = STREAMS_BASE + "%d";
	
	/**
	 * 
	 */
	public static final String STREAM_SUMMARY = STREAMS_BASE + "summary";
	
	/**
	 * 
	 */
	public static final String STREAM_FEATURED = STREAMS_BASE + "featured";
	
	/**
	 * 
	 */
	public static final String STREAMS_FOLLOWED = STREAMS_BASE + "followed";
	
	/* ------------------------------------------------------------ *
	 * ------------------------- Teams ---------------------------- *
	 * ------------------------------------------------------------ */
	
	/**
	 * 
	 */
	public static final String TEAMS_BASE = KRAKEN + "teams";
	
	/**
	 * 
	 */
	public static final String TEAM = TEAMS_BASE + "/%s";
	
	/* ------------------------------------------------------------ *
	 * ------------------------- Users ---------------------------- *
	 * ------------------------------------------------------------ */
	
	/**
	 * 
	 */
	public static final String USER_BASE = KRAKEN + "user";
	
	/**
	 * 
	 */
	public static final String USER_ID = USER_BASE + "s/%d";
	
	/**
	 * 
	 */
	public static final String USERS_GET = USER_BASE + "s?login=%s";
	
	/**
	 * 
	 */
	public static final String USERS_EMOTES = USER_ID + "/emotes";
	
	/**
	 * 
	 */
	public static final String USER_SUBS = USER_ID + "/subscriptions/%d";
	
	/**
	 * 
	 */
	public static final String USER_FOLLOWS = USER_ID + "/follows/channels";
	
	/**
	 * 
	 */
	public static final String USER_FOLLOWs_CHANNEL = USER_FOLLOWS + "/%d";
	
	/**
	 * 
	 */
	public static final String USER_BLOCKS = USER_ID + "/blocks";
	
	/**
	 * 
	 */
	public static final String USER_BLOCK = USER_BLOCKS + "/%d";
	
	/**
	 * 
	 */
	public static final String USER_VHS = USER_BASE + "/vhs";
	
	/* ------------------------------------------------------------ *
	 * ------------------------- Videos --------------------------- *
	 * ------------------------------------------------------------ */
	
	//TODO Later when I implement Video uploads
	
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
