package com.hackhalo2.lib.tweek.twitch.endpoints;

public final class TwitchEndpoints {
	
	private static boolean clientIDSet = false;
	
	public static ChannelFeedEndpointHandler CHANFEED;
	public static ChannelEndpointHandler CHANNEL;
	
	private TwitchEndpoints() { }
	
	public static void setClientID(String clientID) {
		if(!TwitchEndpoints.clientIDSet) {
			TwitchEndpoints.CHANFEED = new ChannelFeedEndpointHandler(clientID);
			TwitchEndpoints.CHANNEL = new ChannelEndpointHandler(clientID);
		}
	}

}
