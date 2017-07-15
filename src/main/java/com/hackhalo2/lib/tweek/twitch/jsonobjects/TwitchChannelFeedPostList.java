package com.hackhalo2.lib.tweek.twitch.jsonobjects;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchChannelFeedPostList {
	
	public String _cursor;
	
	public String _topic;
	
	public boolean _disabled;
	
	private TwitchChannelFeedPost[] posts;
	
	public TwitchChannelFeedPost[] getPosts() {
		return this.posts;
	}
	
	public Optional<TwitchChannelFeedPost> getPostAtIndex(int index) {
		if(this.posts == null || index < 0 || index > (this.posts.length - 1)) return Optional.empty();
		else return Optional.ofNullable(this.posts[index]);
	}

}
