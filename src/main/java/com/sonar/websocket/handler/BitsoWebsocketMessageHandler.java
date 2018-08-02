package com.sonar.websocket.handler;

import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sonar.bean.WebsocketMessage;
import com.sonar.queue.MessagesQueue;

public class BitsoWebsocketMessageHandler extends SonarMessageHandler {

	private Logger logger = Logger.getLogger(BitsoWebsocketMessageHandler.class.getName());
	
	@Override
	public void internalHandleMessage(String message) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			WebsocketMessage websocketMessage = mapper.readValue(message, WebsocketMessage.class);
			MessagesQueue.queueMessage(websocketMessage);
		} catch (Exception e) {
			logger.warning("Cannot read message: " + message);
		}
	}
}
