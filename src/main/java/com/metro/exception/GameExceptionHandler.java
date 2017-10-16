package com.metro.exception;

public class GameExceptionHandler extends Throwable{

	private static final long serialVersionUID = 5193017654637980158L;

	public GameExceptionHandler(String message) {
		super(message,null,false,false);
	}
}
