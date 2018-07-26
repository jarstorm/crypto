package com.jabad.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Operation {

	@JsonProperty("book")
	private String book;
	
	@JsonProperty("price")
	private float price;
	
	@JsonProperty("amount")
	private float amount;
}
