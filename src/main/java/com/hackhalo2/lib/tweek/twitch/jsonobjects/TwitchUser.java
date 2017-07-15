package com.hackhalo2.lib.tweek.twitch.jsonobjects;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchUser {
	
	private long _id;
	
	private String name;
	
	private String display_name;
	
	private String type;
	
	private String bio;
	
	private String logo;
	
	private Date created_at;
	
	private Date updated_at;
	
	public long getID() {
		return this._id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDisplayName() {
		return this.display_name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getBio() {
		return this.bio;
	}
	
	public String getLogo() {
		return this.logo;
	}
	
	public Date getCreatedDate() {
		return this.created_at;
	}
	
	public Date getUpdatedDate() {
		return this.updated_at;
	}
}
