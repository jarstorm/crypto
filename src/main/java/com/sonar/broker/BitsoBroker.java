package com.sonar.broker;

import com.sonar.task.ReloadStateBitso;
import com.sonar.websocket.BitsoWebsocketClient;
import com.sonar.websocket.SonarWebsocketClient;

public class BitsoBroker implements SonarBroker {

	@Override
	public Runnable connect() {
		SonarWebsocketClient clientEndPoint = new BitsoWebsocketClient();

		return new ReloadStateBitso(clientEndPoint);
	}

}
