package com.hackhalo2.lib.tweek.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.hackhalo2.lib.tweek.oauth.IOAuthToken;
import com.hackhalo2.lib.tweek.twitch.endpoints.TwitchEndpoints;

public final class PacketBuilder {
	
	public static final PacketBuilder GENERAL = new PacketBuilder();
	
	public final Packet POST;
	
	public final Packet GET;
	
	public final Packet DELETE;
	
	public final Packet PATCH;
	
	public final Packet PUT;
	
	public PacketBuilder() {
		this.POST = new Packet(HttpPost.class);
		this.GET = new Packet(HttpGet.class);
		this.DELETE = new Packet(HttpDelete.class);
		this.PATCH = new Packet(HttpPatch.class);
		this.PUT = new Packet(HttpPut.class);
	}
	
	public final class Packet {
		
		private final String apiHeader = String.format("application/vnd.twitchtv.v%d+json", TwitchEndpoints.KRAKEN_VERSION);
		
		final Class<? extends HttpUriRequest> requestClass;
		
		private final CloseableHttpClient client = HttpClients.createDefault();
		
		private Packet(Class<? extends HttpUriRequest> clazz) {
			this.requestClass = clazz;
		}
		
		public void sendRequest(String url, String clientID) {
			this.sendRequest(url, clientID, null);
		}
		
		public void sendRequest(String url, IOAuthToken token) {
			this.sendRequest(url, null, token);
		}
		
		public void sendRequest(String url, String clientID, IOAuthToken token) {
			try {
				HttpUriRequest request = this.requestClass.getConstructor(String.class).newInstance(url);
				request.addHeader(HttpHeaders.ACCEPT, this.apiHeader);
				if(clientID != null) request.addHeader("Client-ID", clientID);
				if(token != null) request.addHeader(HttpHeaders.AUTHORIZATION, "OAuth " + token.getToken());
				
				
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
