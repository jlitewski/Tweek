package com.hackhalo2.lib.tweek.twitch.jsonobjects;

import java.util.Date;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchReactCreation {

	private String id;
	
	private String emote_id;
	
	private Date created_at;
	
	private TwitchUser user;
	
	public String getPostID() {
		return this.id;
	}
	
	public String getEmoteID() {
		return this.emote_id;
	}
	
	public Date getCreationDate() {
		return this.created_at;
	}
	
	public Optional<TwitchUser> getUser() {
		return Optional.ofNullable(this.user);
	}
}
