package com.sonar.broker;

import java.util.logging.Logger;

import com.sonar.task.ReloadState;
import com.sonar.task.ReloadStateBitso;
import com.sonar.websocket.BitsoWebsocketClient;
import com.sonar.websocket.SonarWebsocketClient;

public class BitsoBroker extends SonarBroker {	
	
	private static final SonarWebsocketClient websocketClient = new BitsoWebsocketClient();
	
	private static final ReloadState reloadState = new ReloadStateBitso();
	
	@Override
	public boolean hasWebsocketSupport() {
		return true;
	}
	
	@Override
	public String getName() {
		return "Bitso";
	}
	
	@Override
	public SonarWebsocketClient getWebsocketClass() {
		return websocketClient;
	}
	
	@Override
	public ReloadState getReloadStateTask() {
		return reloadState;
	}		

}
