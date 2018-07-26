package com.jabad.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BtcBook {
	@JsonProperty("success")
	private Boolean success;
	
	@JsonProperty("payload")
	private RestPayload payload;
}
