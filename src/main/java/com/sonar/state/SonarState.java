package com.sonar.state;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.sonar.broker.SonarBroker;

public class SonarState {

	private static SonarState instance;
	
	private SonarState() {}
	
	
	public static SonarState getInstance() {
		if (instance == null) {
			instance = new SonarState();
		}
		return instance;		
	}
	
	private static final ScheduledExecutorService scheduler =
		     Executors.newScheduledThreadPool(1);
		
	private List<SonarBroker> brokers;
	
	public SonarState(List<SonarBroker> brokers) {
		this.brokers = brokers;
		connectAll(brokers);
	}
	
	/**
	 * Connect all brokers
	 * @param brokers
	 */
	private void connectAll(List<SonarBroker> brokers) {
		for(SonarBroker broker: brokers) {
			Runnable reloadStateStak = broker.connect();
			scheduler.scheduleAtFixedRate(reloadStateStak, 1, 3L , TimeUnit.SECONDS);
		}
	}


	/**
	 * Number of operations to show
	 */
	private int numberOfOperations;
	
	
}
