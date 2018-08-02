package com.sonar.broker;

import java.util.logging.Logger;

import com.sonar.task.ReloadState;
import com.sonar.websocket.SonarWebsocketClient;

public abstract class SonarBroker {

	private Logger logger = Logger.getLogger(SonarBroker.class.getName());
	
	public abstract boolean hasWebsocketSupport();
	
	public abstract String getName();

	public abstract SonarWebsocketClient getWebsocketClass();
	
	public abstract ReloadState getReloadStateTask(String name);
	
	public Runnable connect() {
		SonarWebsocketClient clientEndPoint = null;
		if (hasWebsocketSupport()) {
			logger.info("Connect websocket " + getName());
			clientEndPoint = getWebsocketClass();
		} else {
			logger.info(getName() + " has no websocket support");
		}
		return getReloadStateTask(getName());
	}

}
