package com.hackhalo2.lib.tweek.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.hackhalo2.lib.tweek.exceptions.RestAPIException;
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
		
		public <T> T sendRequest(String url, Class<T> clazz)
				throws RestAPIException {
			return this.sendRequest(url, clazz, null);
		}
		
		public <T> T sendJSONRequest(String url, JsonNode json, Class<T> clazz)
				throws RestAPIException {
			return this.sendJSONRequest(url, json, clazz, null);
		}
		
		public <T> T sendRequest(String url, Class<T> clazz, IOAuthToken token)
				throws RestAPIException {
			byte[] result = this.sendRequestRaw(url, token);
			return this.serializeResult(result, clazz);
		}
		
		public <T> T sendJSONRequest(String url, JsonNode json, Class<T> clazz, IOAuthToken token)
				throws RestAPIException {
			byte[] result = this.sendJSONRequestRaw(url, json, token);
			return this.serializeResult(result, clazz);
		}
		
		public byte[] sendRequestRaw(String url, IOAuthToken token)
				throws RestAPIException {
			byte[] data = new byte[0];
			
			try {
				HttpUriRequest request = this.requestClass.getConstructor(String.class).newInstance(url);
				request.addHeader(HttpHeaders.ACCEPT, this.apiHeader);
				if(this.clientID != null) request.addHeader("Client-ID", this.clientID);
				if(token != null) request.addHeader(HttpHeaders.AUTHORIZATION, "OAuth " + token.getToken());
				
				try(CloseableHttpResponse response = this.client.execute(request)) {
					this.checkStatusCode(response.getStatusLine().getStatusCode());
					
					if(response.getEntity() != null)
						data = EntityUtils.toByteArray(response.getEntity());
				} catch (Exception e) {
					throw new IOException("There was a problem with handling the response data!", e);
				}
			} catch (Exception e) {
				throw new RestAPIException("Tweek REST API Exception", e);
			}
			
			return data;
		}
		
		public byte[] sendJSONRequestRaw(String url, JsonNode json, IOAuthToken token)
				throws RestAPIException {
			byte[] data = new byte[0];
			
			try {
				if(HttpEntityEnclosingRequestBase.class.isAssignableFrom(this.requestClass)) {
					HttpEntityEnclosingRequestBase request = (HttpEntityEnclosingRequestBase)this.requestClass.getConstructor(String.class).newInstance(url);
					request.addHeader(HttpHeaders.ACCEPT, this.apiHeader);
					request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
					if(this.clientID != null) request.addHeader("Client-ID", this.clientID);
					if(token != null) request.addHeader(HttpHeaders.AUTHORIZATION, "OAuth " + token.getToken());
					
					HttpEntity entity = new StringEntity(TweekUtils.OBJMAP.writeValueAsString(json), "UTF-8");
					request.setEntity(entity);
					
					try(CloseableHttpResponse response = this.client.execute(request)) {
						this.checkStatusCode(response.getStatusLine().getStatusCode());
						
						if(response.getEntity() != null)
							data = EntityUtils.toByteArray(response.getEntity());
					} catch (Exception e) {
						throw new IOException("There was a problem with handling the response data!", e);
					}
				} else {
					throw new HttpException(String.format("Attempted to attach HttpEntity to invalid class! requestClass=%s", this.requestClass.getSimpleName()));
				}
			} catch (Exception e) {
				throw new RestAPIException("Tweek REST API Exception", e);
			}
			
			return data;
		}
		
		private void checkStatusCode(int statusCode) throws HttpResponseException {
			String error = null;
			switch(statusCode) {
			case 400:
				error = "Request Not Valid. Something is wrong with the request. Double check your code, and if it's correct, let the Dev know ASAP!";
				break;
			case 401:
				error = "Unauthorized. The OAuth token does not have the correct scope or does not have the required permission on behalf of the specified user.";
				break;
			case 403:
				error = "Forbidden. This usually indicates that authentication was provided, but the authenticated user is not permitted to perform the requested operation.";
				break;
			case 404:
				error = "Not Found. It seems whatever you are looking for is in another castle.";
				break;
			case 422:
				error = "Unprocessable Entity. Probably used an API endpoint for something that couldn't use it.";
				break;
			case 429:
				error = "Too Many Requests. Slow down there Sonic.";
				break;
			case 500:
				error = "Internal Server Error. Welp... Not much we can do with this one, Doc.";
				break;
			case 503:
				error = "Service Unavailable. Gonna have to try again";
				break;
			default:
				//Log the code for debugging purposes
				break;
			}
			
			if(error != null) throw new HttpResponseException(statusCode, error);
		}
		
		private <T> T serializeResult(byte[] result, Class<T> clazz) throws RestAPIException {
			try {
				if(result == null) return null;
				if(result.length == 0) return null;
				
				return TweekUtils.OBJMAP.readValue(result, clazz);
			} catch(Exception e) {
				throw new RestAPIException("Failed to serialize JSON result!", e);
			}
		}
		
	}

}
