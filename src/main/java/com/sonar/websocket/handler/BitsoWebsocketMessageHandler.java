package com.sonar.websocket.handler;

import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sonar.bean.WebsocketMessage;
import com.sonar.queue.MessagesQueue;

public class BitsoWebsocketMessageHandler implements SonarMessageHandler {

	private Logger logger = Logger.getLogger(BitsoWebsocketMessageHandler.class.getName());
	
	@Override
	public void handleMessage(String message) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(message);
			WebsocketMessage websocketMessage = mapper.readValue(message, WebsocketMessage.class);
			MessagesQueue.queueMessage(websocketMessage);
		} catch (Exception e) {
			logger.warning("Cannot read message: " + message);
		}
	}
}
