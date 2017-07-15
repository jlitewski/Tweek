package com.hackhalo2.lib.tweek.twitch.jsonobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchReaction {
	
	public long count;
	
	private String emote;
	
	private long[] user_ids;
	
	public String getEmoteName() {
		return this.emote;
	}
	
	public long[] getReactedUserIDs() {
		return this.user_ids;
	}
	
	public boolean hasUserReacted(long userId) {
		for(long user : this.user_ids) {
			if(user == userId) return true;
		}
		
		return false;
	}

}
