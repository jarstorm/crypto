package com.sonar.websocket.handler;

import javax.websocket.MessageHandler;

public interface SonarMessageHandler extends MessageHandler {

	void handleMessage(String message);
}
