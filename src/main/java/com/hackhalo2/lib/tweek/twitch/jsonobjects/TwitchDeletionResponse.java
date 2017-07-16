package com.hackhalo2.lib.tweek.twitch.jsonobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchDeletionResponse {

	private boolean deleted;
	
	public boolean wasDeleted() {
		return this.deleted;
	}
}
