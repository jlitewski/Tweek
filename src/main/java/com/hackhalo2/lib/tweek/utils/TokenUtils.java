package com.hackhalo2.lib.tweek.utils;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

public class TokenUtils {

	private TokenUtils() { }
	
	public static String formatURLEncodedString(Collection<String> scopes) {
		return StringUtils.join(scopes, "+");
	}
	
	public static String formatHumanReadable(Collection<String> scopes) {
		return StringUtils.join(scopes, ", ");
	}
}
