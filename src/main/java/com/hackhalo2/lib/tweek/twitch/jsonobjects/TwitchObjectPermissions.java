package com.hackhalo2.lib.tweek.twitch.jsonobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchObjectPermissions {
	
	public boolean can_delete = false;
	public boolean can_moderate = false;
	public boolean can_reply = false;

}
