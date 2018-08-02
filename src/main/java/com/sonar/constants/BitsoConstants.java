package com.sonar.constants;

public class BitsoConstants {

	public final static String REST_URI = "https://api.bitso.com/v3/order_book/?book=btc_mxn";
		
	public final static String WEBSOCKET_URI = "wss://ws.bitso.com";	

	public final static String SUBSCRIPTION_MESSAGE = "{\"action\":\"subscribe\", \"book\":\"btc_mxn\",\"type\":\"diff-orders\"}";
}
