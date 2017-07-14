package com.hackhalo2.lib.tweek.twitch.jsonobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchUserList {
	
	public int _total;
	
	public TwitchUser[] users;

}
