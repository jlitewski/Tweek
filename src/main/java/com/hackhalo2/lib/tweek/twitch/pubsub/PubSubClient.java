package com.hackhalo2.lib.tweek.twitch.pubsub;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hackhalo2.lib.tweek.twitch.TweekUtils;
import com.hackhalo2.lib.tweek.twitch.TwitchEndpoints;

public class PubSubClient {

	private Set<String> subscribedTopics = new HashSet<>();
	private PubSubClientSocket socket;
	private WebSocketClient wsc = new WebSocketClient();

	public PubSubClient() {
		this.socket = new PubSubClientSocket(this);
	}


	/**
	 *  
	 * @param type
	 * @param channelID the _id field returned by the Twitch API Get Channel endpoint.
	 */
	public void channelBitTopic(SubscribeType type, long channelID) {
		try {
			this.checkSocket();
			String request = String.format(TwitchEndpoints.PUBSUB_CHANNEL_BITS, TwitchEndpoints.PUBSUB_VERSION, channelID);
			JsonNode node = this.generateJson(type, request);

			//null means that we either already are listening to a topic we tried to listen to, or are not listening to a topic we tried to unlisten
			if(node != null) {
				this.socket.sendJsonBlob(node);
			}
		} catch(Exception e) {
			//TODO: Log error
			return;
		}
	}

	/**
	 * 
	 * @param type
	 * @param channelID the _id field returned by the Twitch API Get Channel endpoint.
	 */
	public void channelSubscribeTopic(SubscribeType type, long channelID) {
		try {
			this.checkSocket();
			String request = String.format(TwitchEndpoints.PUBSUB_CHANNEL_SUB, TwitchEndpoints.PUBSUB_VERSION, channelID);
			JsonNode node = this.generateJson(type, request);

			//null means that we either already are listening to a topic we tried to listen to, or are not listening to a topic we tried to unlisten
			if(node != null) {
				this.socket.sendJsonBlob(node);
			}
		} catch(Exception e) {
			//TODO: Log error
			return;
		}
	}

	/**
	 * 
	 * @param type
	 * @param channelID the _id field returned by the Twitch API Get Channel endpoint.
	 */
	public void channelCommerceTopic(SubscribeType type, long channelID) {
		try {
			this.checkSocket();
			String request = String.format(TwitchEndpoints.PUBSUB_CHANNEL_COMMERCE, TwitchEndpoints.PUBSUB_VERSION, channelID);
			JsonNode node = this.generateJson(type, request);

			//null means that we either already are listening to a topic we tried to listen to, or are not listening to a topic we tried to unlisten
			if(node != null) {
				this.socket.sendJsonBlob(node);
			}
		} catch(Exception e) {
			//TODO: Log error
			return;
		}
	}

	/**
	 * 
	 * @param type
	 * @param userID the _id field returned by the Twitch API Get User endpoint.
	 */
	public void userWhisperTopic(SubscribeType type, long userID) {
		try {
			this.checkSocket();
			String request = String.format(TwitchEndpoints.PUBSUB_USER_WHISPER, TwitchEndpoints.PUBSUB_VERSION, userID);
			JsonNode node = this.generateJson(type, request);

			//null means that we either already are listening to a topic we tried to listen to, or are not listening to a topic we tried to unlisten
			if(node != null) {
				this.socket.sendJsonBlob(node);
			}
		} catch(Exception e) {
			//TODO: Log error
			return;
		}
	}

	private void checkSocket() throws Exception {
		if(!this.wsc.isStarted() && !this.wsc.isStarting()) this.wsc.start(); //Start the client if it isn't already started

		while(this.wsc.isStarting()) { ;; } //wait for the client to start fully

		if(!this.socket.isConnected() || this.socket.shouldReconnect()) { //If the socket isn't connected
			ClientUpgradeRequest upgrade = new ClientUpgradeRequest();
			this.wsc.connect(this.socket, new URI(TwitchEndpoints.PUBSUB), upgrade);
			this.socket.getLatch().await(); //wait for things to synchronize
		}
	}

	private JsonNode generateJson(SubscribeType type, String request) {
		ObjectNode node = TweekUtils.OBJMAP.createObjectNode();
		ObjectNode data = TweekUtils.OBJMAP.createObjectNode();
		ArrayNode topics = TweekUtils.OBJMAP.createArrayNode();
		String nonce = TweekUtils.generateNonce();

		//Are we listening to a new Topic, or unlistening to one?
		if(type.equals(SubscribeType.LISTEN)) {
			if(this.subscribedTopics.contains(request)) return null; //We are already listening to this request, don't resend it

			this.subscribedTopics.add(request); //add the request to the list of ones we are listening to
			node.put("type", "LISTEN");
		} else {
			if(!this.subscribedTopics.contains(request)) return null; //We are not listening to this event already, don't send the request

			this.subscribedTopics.remove(request);
			node.put("type", "UNLISTEN");
		}

		node.put("nonce", nonce);

		topics.add(request);
		data.set("topics", topics);
		data.put("auth_token", "TODO");

		node.set("data", data);

		return node;
	}

	public enum SubscribeType {
		LISTEN,
		UNLISTEN
	}

}
