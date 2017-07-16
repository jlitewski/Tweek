package com.hackhalo2.lib.tweek.exceptions;

public class MissingScopeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3484747312972894300L;
	
	private final String scope;

	public MissingScopeException(String message, String scope) {
		super(message);
		this.scope = scope;
	}
	
	public MissingScopeException(String message, Throwable cause, String scope) {
		super(message, cause);
		this.scope = scope;
	}
	
	public String getMissingScope() {
		return this.scope;
	}
}
