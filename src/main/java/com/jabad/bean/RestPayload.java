package com.jabad.bean;

import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RestPayload {

	@JsonProperty("updated_at")
	private Calendar updatedAt;
	
	@JsonProperty("sequence")
	private long sequence;
	
	@JsonProperty("asks")
	private List<Operation> asks;
	
	@JsonProperty("bids")
	private List<Operation> bids;
}
