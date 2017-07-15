package com.hackhalo2.lib.tweek.twitch.jsonobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchEmote {

	private String end;
	private String id;
	private String set;
	private String start;
	
	public String getEndPoint() {
		return this.end;
	}
	
	public String getStartPoint() {
		return this.start;
	}
	
	public String getEmoteSet() {
		return this.set;
	}
	
	public String getEmoteID() {
		return this.id;
	}
}
