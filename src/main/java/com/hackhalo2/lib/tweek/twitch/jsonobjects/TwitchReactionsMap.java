package com.hackhalo2.lib.tweek.twitch.jsonobjects;

import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitchReactionsMap {
	
	private Map<String, TwitchReaction> reactions;
	
	public Optional<Map<String, TwitchReaction>> getReactions() {
		return Optional.ofNullable(this.reactions);
	}
	
	public boolean hasReaction(String reactionName) {
		if(this.reactions == null) return false;
		
		return this.reactions.containsKey(reactionName);
	}
	
	public Optional<TwitchReaction> getReaction(String reactionName) {
		if(this.reactions == null) return Optional.empty();
		
		return Optional.ofNullable(this.reactions.get(reactionName));
	}

}
