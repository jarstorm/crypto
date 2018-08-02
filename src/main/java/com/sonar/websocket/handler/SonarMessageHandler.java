package com.sonar.websocket.handler;

import javax.websocket.MessageHandler;

public abstract class SonarMessageHandler implements MessageHandler {

	public void handleMessage(String message) {
		// If has access -> call internal handle
		// Else store in a queue
		internalHandleMessage(message);
	};
	
	public abstract void internalHandleMessage(String message);
}
