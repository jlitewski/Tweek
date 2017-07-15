package com.hackhalo2.lib.tweek.twitch.jsonobjects;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchCommentList {
	
	public String _cursor;
	
	public long _total;
	
	private TwitchComment[] comments;
	
	public Optional<TwitchComment[]> getComments() {
		return Optional.ofNullable(this.comments);
	}
	
	public Optional<TwitchComment> getCommentAtIndex(int index) {
		if(index < 0 || index > (this.comments.length - 1)) return Optional.empty();
		else return Optional.ofNullable(this.comments[index]);
	}

}
