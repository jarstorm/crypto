package com.sonar.websocket;

import java.net.URI;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sonar.bean.WebsocketMessage;
import com.sonar.queue.MessagesQueue;
import com.sonar.websocket.handler.SonarMessageHandler;

public abstract class SonarWebsocketClient {

	private Logger logger = Logger.getLogger(SonarWebsocketClient.class.getName());
	private Session userSession = null;
	private SonarMessageHandler messageHandler;	

	public SonarWebsocketClient() {
		try {
			System.out.println("Connect websocket " + getBrokerName());
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			container.connectToServer(this, new URI(getUrl()));
			addMessageHandler(getMessageHandler());
			
			sendMessage(getWebsocketSubscriptionMessage());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Callback hook for Connection open events.
	 *
	 * @param userSession
	 *            the userSession which is opened.
	 */
	@OnOpen
	public void onOpen(Session userSession) {
		System.out.println("Opening websocket");
		this.userSession = userSession;
	}

	/**
	 * Callback hook for Connection close events.
	 *
	 * @param userSession
	 *            the userSession which is getting closed.
	 * @param reason
	 *            the reason for connection close
	 */
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		System.out.println("closing websocket");
		this.userSession = null;
	}

	/**
	 * Callback hook for Message Events. This method will be invoked when a client
	 * send a message.
	 *
	 * @param message
	 *            The text message
	 */
	@OnMessage
	public void onMessage(String message) {
		if (this.messageHandler != null) {
			this.messageHandler.handleMessage(message);
		}
	}

	/**
	 * register message handler
	 *
	 * @param msgHandler
	 */
	public void addMessageHandler(SonarMessageHandler msgHandler) {
		this.messageHandler = msgHandler;
	}

	/**
	 * Send a message.
	 *
	 * @param message
	 */
	public void sendMessage(String message) {
		this.userSession.getAsyncRemote().sendText(message);
	}

	public abstract String getUrl();
	
	public abstract String getWebsocketSubscriptionMessage();
	
	public abstract String getBrokerName();
	
	public abstract SonarMessageHandler getMessageHandler();
}
