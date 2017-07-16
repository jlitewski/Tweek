package com.hackhalo2.lib.tweek.exceptions;

public class EndpointException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7130390867503373075L;

	public EndpointException(String message) {
		super(message);
	}
	
	public EndpointException(String message, Throwable cause) {
		super(message, cause);
	}
}
