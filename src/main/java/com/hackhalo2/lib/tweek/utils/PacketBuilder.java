package com.hackhalo2.lib.tweek.utils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.hackhalo2.lib.tweek.oauth.IOAuthToken;
import com.hackhalo2.lib.tweek.twitch.endpoints.TwitchURLEndpoints;

public final class PacketBuilder {
	
	public static final PacketBuilder GENERAL = new PacketBuilder(null);
	
	public final Packet POST;
	
	public final Packet GET;
	
	public final Packet DELETE;
	
	public final Packet PATCH;
	
	public final Packet PUT;
	
	public PacketBuilder(String clientID) {
		this.POST = new Packet(clientID, HttpPost.class);
		this.GET = new Packet(clientID, HttpGet.class);
		this.DELETE = new Packet(clientID, HttpDelete.class);
		this.PATCH = new Packet(clientID, HttpPatch.class);
		this.PUT = new Packet(clientID, HttpPut.class);
	}
	
	public final class Packet {
		
		private final String apiHeader = String.format("application/vnd.twitchtv.v%d+json", TwitchURLEndpoints.KRAKEN_VERSION);
		
		final Class<? extends HttpUriRequest> requestClass;
		
		private final CloseableHttpClient client = HttpClients.createDefault();
		
		private final String clientID;
		
		private Packet(String clientID, Class<? extends HttpUriRequest> clazz) {
			this.clientID = clientID;
			this.requestClass = clazz;
		}
		
		public <T> T sendRequest(String url, Class<T> clazz) {
			byte[] result = this.sendRequestRaw(url, null);
			return result == null ? null : this.serializeResult(result, clazz);
		}
		
		public <T> T sendRequest(String url, Class<T> clazz, IOAuthToken token) {
			byte[] result = this.sendRequestRaw(url, token);
			return result == null ? null : this.serializeResult(result, clazz);
		}
		
		public byte[] sendRequestRaw(String url, IOAuthToken token) {
			byte[] data = new byte[0];
			try {
				HttpUriRequest request = this.requestClass.getConstructor(String.class).newInstance(url);
				request.addHeader(HttpHeaders.ACCEPT, this.apiHeader);
				if(this.clientID != null) request.addHeader("Client-ID", this.clientID);
				if(token != null) request.addHeader(HttpHeaders.AUTHORIZATION, "OAuth " + token.getToken());
				
				try(CloseableHttpResponse response = this.client.execute(request)) {
					if(response.getEntity() != null)
						data = EntityUtils.toByteArray(response.getEntity());
				} catch (IOException e) {
					//TODO: Complain and moan
				}
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return data;
		}
		
		private <T> T serializeResult(byte[] result, Class<T> clazz) {
			try {
				return TweekUtils.OBJMAP.readValue(result, clazz);
			} catch(Exception e) {
				//TODO: Log and bitch
				return null;
			}
		}
		
	}

}
