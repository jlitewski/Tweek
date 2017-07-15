package com.hackhalo2.lib.tweek.twitch.jsonobjects;

import java.util.Date;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchChannelFeedPost {

	private String body;
	
	private Date created_at;
	
	private boolean deleted;
	
	private String id;
	
	private TwitchReactionsMap reactions;
	
	private TwitchEmote[] emotes;
	
	public TwitchObjectPermissions permissions;
	
	private TwitchUser user;
	
	//TODO: Implement embeds once I figure out how they work
	
	public String getBody() {
		return this.body;
	}
	
	public Date createdOn() {
		return this.created_at;
	}
	
	public boolean wasDeleted() {
		return this.deleted;
	}
	
	public String getID() {
		return this.id;
	}
	
	public Optional<TwitchReactionsMap> getReactions() {
		return Optional.ofNullable(this.reactions);
	}
	
	public Optional<TwitchUser> getPoster() {
		return Optional.ofNullable(this.user);
	}
	
	public Optional<TwitchEmote[]> getEmotes() {
		return Optional.ofNullable(this.emotes);
	}
	
	public Optional<TwitchEmote> getEmoteAtIndex(int index) {
		if(this.emotes == null || index < 0 || index > (this.emotes.length - 1)) return Optional.empty();
		else return Optional.ofNullable(this.emotes[index]);
	}
	
}
