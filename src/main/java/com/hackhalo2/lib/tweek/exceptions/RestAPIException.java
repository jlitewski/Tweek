package com.hackhalo2.lib.tweek.exceptions;

public class RestAPIException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9012146923524390697L;

	public RestAPIException(String message) {
		super(message);
	}
	
	public RestAPIException(String message, Throwable cause) {
		super(message, cause);
	}
}
