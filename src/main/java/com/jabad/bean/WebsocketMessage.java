package com.jabad.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebsocketMessage {

	@JsonProperty("type")
	private String type;
	
	@JsonProperty("book")
	private String book;
	
	@JsonProperty("sequence")
	private long sequence;
	
	@JsonProperty("payload")
	private List<WebsocketPayload> payload;
}
