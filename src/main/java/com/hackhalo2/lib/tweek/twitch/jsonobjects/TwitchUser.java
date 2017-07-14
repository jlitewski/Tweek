package com.hackhalo2.lib.tweek.twitch.jsonobjects;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchUser {
	
	public long _id;
	
	public String name;
	
	public String display_name;
	
	public String type;
	
	public String bio;
	
	public String logo;
	
	public Date created_at;
	
	public Date updated_at;
}
