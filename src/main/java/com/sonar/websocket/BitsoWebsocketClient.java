package com.sonar.websocket;

import javax.websocket.ClientEndpoint;
import javax.websocket.MessageHandler;

import com.sonar.constants.BitsoConstants;
import com.sonar.websocket.handler.BitsoWebsocketMessageHandler;
import com.sonar.websocket.handler.SonarMessageHandler;

@ClientEndpoint
public class BitsoWebsocketClient extends SonarWebsocketClient {

	private static SonarMessageHandler messageHandler = new BitsoWebsocketMessageHandler();
	
	@Override
	public String getUrl() {
		return BitsoConstants.WEBSOCKET_URI;
	}

	@Override
	public String getWebsocketSubscriptionMessage() {
		return BitsoConstants.SUBSCRIPTION_MESSAGE;
	}

	@Override
	public String getBrokerName() {
		return "Bitso";
	}

	@Override
	public SonarMessageHandler getMessageHandler() {
		return messageHandler;
	}

	
}