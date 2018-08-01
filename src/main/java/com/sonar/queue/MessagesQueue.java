package com.sonar.queue;

import java.util.ArrayList;
import java.util.List;

import com.sonar.bean.WebsocketMessage;

public class MessagesQueue {

	private static List<WebsocketMessage> messagesQueue = new ArrayList<>();
	
	public static void queueMessage(WebsocketMessage message) {
		messagesQueue.add(message);
		System.out.println(message);
	}
}
