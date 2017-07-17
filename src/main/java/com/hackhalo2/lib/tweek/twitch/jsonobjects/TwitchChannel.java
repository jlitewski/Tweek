package com.hackhalo2.lib.tweek.twitch.jsonobjects;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchChannel {

	public long _id;
	
	private String broadcaster_language;
	
	private Date created_at;
	
	private String display_name;
	
	private long followers;
	
	private String game;
	
	private String language;
	
	private String logo;
	
	private boolean mature;
	
	private String name;
	
	private boolean partner;
	
	//TODO: Figure out what profile_banner and profile_banner_background_color return (Most likely String, but better off ignoring it for now)
	
	private String status;
	
	private Date updated_at;
	
	private String url;
	
	private String video_banner;
	
	private long views;
	
	public String getBroadcasterLanguage() {
		return this.broadcaster_language;
	}
	
	public Date createdOn() {
		return this.created_at;
	}
	
	public String getDisplayName() {
		return this.display_name;
	}
	
	public long numberOfFollowers() {
		return this.followers;
	}
	
	public String getGame() {
		return this.game;
	}
	
	public String getLanguage() {
		return this.language;
	}
	
	public URL getLogoURL() throws MalformedURLException {
		return new URL(this.logo);
	}
	
	public boolean isMature() {
		return this.mature;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isPartner() {
		return this.partner;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public Date updatedOn() {
		return this.updated_at;
	}
	
	public URL getChannelURL() throws MalformedURLException {
		return new URL(this.url);
	}
	
	public URL getVideoBanner() throws MalformedURLException {
		return new URL(this.video_banner);
	}
	
	public long numberOfViews() {
		return this.views;
	}
	
}
