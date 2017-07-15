package com.hackhalo2.lib.tweek.twitch.jsonobjects;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchUserList {
	
	public int _total;
	
	private TwitchUser[] users;
	
	public Optional<TwitchUser[]> getUsers() {
		return Optional.ofNullable(this.users);
	}
	
	public Optional<TwitchUser> getUserAtIndex(int index) {
		if(index < 0 || index > (this.users.length - 1)) return Optional.empty();
		else return Optional.ofNullable(this.users[index]);
	}

}
