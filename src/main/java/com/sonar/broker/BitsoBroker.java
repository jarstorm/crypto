package com.sonar.broker;

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
	public ReloadState getReloadStateTask(String name) {
		reloadState.setBrokerName(name);
		return reloadState;
	}		

}
