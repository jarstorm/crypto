package com.sonar.bean;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WebsocketPayload {

	@JsonProperty("o")
	private String order;
	
	@JsonProperty("d")
	private Calendar time;
	
	@JsonProperty("r")
	private float rate;
	
	@JsonProperty("t")
	private boolean operationType;
	
	@JsonProperty("a")
	private String amount;
	
	@JsonProperty("v")
	private String value;
	
	@JsonProperty("s")
	private String status;		
	
}
