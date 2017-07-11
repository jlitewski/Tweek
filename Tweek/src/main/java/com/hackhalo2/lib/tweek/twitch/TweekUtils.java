package com.hackhalo2.lib.tweek.twitch;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.PassiveExpiringMap;
import org.apache.commons.text.RandomStringGenerator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

public final class TweekUtils {
	
	/**
	 * A Cache for past REST calls.<br />
	 * This is useful to keep API calls to a minimum, and to keep Twitch happy
	 */
	public static Map<String, Object> restCache = new PassiveExpiringMap<>(5, TimeUnit.MINUTES);
	
	/**
	 * 
	 */
	private static RandomStringGenerator nonceGen = new RandomStringGenerator.Builder().build();
	
	public static final ObjectMapper OBJMAP = new ObjectMapper()
			.registerModule(new AfterburnerModule())
			.enable(SerializationFeature.USE_EQUALITY_FOR_OBJECT_ID)
			.enable(SerializationFeature.WRITE_NULL_MAP_VALUES)
			.enable(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY)
			.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
			.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
			.enable(JsonParser.Feature.ALLOW_COMMENTS)
			.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
			.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES)
			.enable(JsonParser.Feature.ALLOW_MISSING_VALUES)
			.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
			.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

	public static String generateNonce() {
		return TweekUtils.nonceGen.generate(16);
	}
}
