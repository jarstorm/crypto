package com.sonar.task;

import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sonar.bean.BtcBook;
import com.sonar.constants.BitsoConstants;
import com.sonar.state.SonarState;

public class ReloadStateBitso implements ReloadState {	
	private static Logger logger = Logger.getLogger(ReloadStateBitso.class.getName());
	private String brokerName;
		
	@Override
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	
	@Override
	public void run() {
		logger.info("Reloading bitso state");
		reloadRestState();
	}
	
	private void reloadRestState() {
		SonarState.getInstance().getasd
		final Client client = ClientBuilder.newClient();
		WebTarget resource = client.target(BitsoConstants.REST_URI);
		Response response = resource.request(MediaType.APPLICATION_JSON).get();
		if (Status.OK.getStatusCode() == response.getStatus()) {
			// String value = response.readEntity(String.class);
			BtcBook value = response.readEntity(BtcBook.class);
			System.out.println(value);
		} else {
			System.err.println("error");
		}
	}

	

}
