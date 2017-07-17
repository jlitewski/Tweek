package com.hackhalo2.lib.tweek.twitch;

import org.apache.commons.lang3.StringUtils;

public enum TwitchScope {

	CHANNEL_CHECK_SUBSCRIPTION,
	CHANNEL_COMMERCIAL,
	CHANNEL_EDITOR,
	CHANNEL_READ,
	CHANNEL_FEED_EDIT,
	CHANNEL_FEED_READ,
	CHANNEL_STREAM,
	CHANNEL_SUBSCRIPTIONS,
	CHAT_LOGIN,
	COLLECTIONS_EDIT,
	COMMUNITIES_EDIT,
	COMMUNITIES_MODERATE,
	OPENID,
	USER_BLOCKS_EDIT,
	USER_BLOCKS_READ,
	USER_FOLLOWS_EDIT,
	USER_READ,
	USER_SUBSCRIPTIONS,
	VIEWING_ACTIVITY_READ;
	
	private TwitchScope() { }
	
	public static String toString(TwitchScope scope) {
		return scope.name().toLowerCase();
	}
	
	public static TwitchScope toScope(String scope) {
		for(TwitchScope all : TwitchScope.values()) {
			if(StringUtils.equalsIgnoreCase(scope, all.name())) {
				return all;
			}
		}
		
		return null; //Scope not defined
	}
}
