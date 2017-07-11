package com.hackhalo2.lib.tweek.twitch.pubsub;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hackhalo2.lib.tweek.twitch.TweekUtils;

@WebSocket
public class PubSubClientSocket {

	private final PubSubClient client;
	private Session session;
	private CountDownLatch latch = new CountDownLatch(1);
	private Timer timer = new Timer();
	private boolean recievedPong = false;
	private boolean shouldReconnect = false;
	
	protected PubSubClientSocket(final PubSubClient client) {
		this.client = client;
	}

	@OnWebSocketConnect
	public void onConnect(Session session) {
		this.session = session;
		this.shouldReconnect = false;
		this.latch.countDown();

		//Setup a ping timer to keep the websocket alive
		//Twitch wants a PING at least once every 5 minutes
		this.timer.scheduleAtFixedRate(new TimerTask()
		{
			@Override
			public void run() {
				try {
					ObjectNode node = TweekUtils.OBJMAP.createObjectNode();
					node.put("type", "PING");
					session.getRemote().sendString(TweekUtils.OBJMAP.writeValueAsString(node));
					timer.schedule(new TimerTask()
					{
						@Override
						public void run() {
							if(!recievedPong) {
								session.close(-1, "PubSub Server failed to send PONG, reconnecting...");
								PubSubClientSocket.this.reset();
								return;
							}
							
							recievedPong = false;
						}
					}, 11000);
				} catch (Exception e) {
					session.close(-1, "Failed to PING PubSub Server, reconnecting...");
					PubSubClientSocket.this.reset();
				}
			}

		}, 7000, 282000);
	}

	@OnWebSocketMessage
	public void onMessage(Session session, String message) {
		try {
			JsonNode node = TweekUtils.OBJMAP.readTree(message);
			
			if(!node.has("type") || node.get("type") == null) {
				//TODO: Log the garbage we got
				return;
			}
			
			if(node.has("error") && node.get("error") != null && node.get("error").textValue().length() > 0) {
				//TODO: Log the Error PubSub sent us
				this.session.close(-1, "PubSub encountered an error, reconnecting...");
				this.reset();
				return;
			}
			
			switch(node.get("type").textValue().toLowerCase()) {
			case "pong":
				this.recievedPong = true;
				break;
			case "reconnect":
				this.session.close(-1, "Recieved RECONNECT from PubSub Server, reconnecting...");
				this.reset();
				break;
			case "message":
				//TODO: parse messages
				break;
			default:
				//TODO: Log this, since PubSub added a new type
				break;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendJsonBlob(JsonNode blob) throws IOException {
		if(this.isConnected()) {
			this.session.getRemote().sendString(TweekUtils.OBJMAP.writeValueAsString(blob));
		}
	}
	
	public CountDownLatch getLatch() {
		return this.latch;
	}
	
	public boolean shouldReconnect() {
		return this.shouldReconnect;
	}
	
	public boolean isConnected() {
		if(this.session == null) return false;
		
		return this.session.isOpen();
	}
	
	private void reset() {
		this.latch = new CountDownLatch(1);
		this.shouldReconnect = true;
	}

}
