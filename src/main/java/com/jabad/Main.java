package com.jabad;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jabad.bean.BtcBook;
import com.jabad.bean.WebsocketMessage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
	private static Logger logger = Logger.getLogger(Application.class.getName());
	
	private static final String SUBSCRIPTION_MESSAGE = "{\"action\":\"subscribe\", \"book\":\"btc_mxn\",\"type\":\"diff-orders\"}";
	private static final String WEBSOCKET_URI = "wss://ws.bitso.com";
	private static final String REST_URI = "https://api.bitso.com/v3/order_book/?book=btc_mxn";

	public static void main(String[] args) throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException {
		System.out.println("Connect websocket");

		WebsocketClientEndpoint clientEndPoint;
		try {
			clientEndPoint = new WebsocketClientEndpoint(new URI(WEBSOCKET_URI));

			// add listener
			clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
				public void handleMessage(String message) {					
					ObjectMapper mapper = new ObjectMapper();
					try {
						System.out.println(message);
						WebsocketMessage websocketMessage = mapper.readValue(message, WebsocketMessage.class);
						System.out.println(websocketMessage);
					} catch (Exception e) {
						logger.warning("Cannot read message: " + message);
					}
				}
			});

			clientEndPoint.sendMessage(SUBSCRIPTION_MESSAGE);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		final Client client = ClientBuilder.newClient();

		WebTarget resource = client.target(REST_URI);
		Response response = resource.request(MediaType.APPLICATION_JSON).get();
		if (Status.OK.getStatusCode() == response.getStatus()) {
			// String value = response.readEntity(String.class);
			BtcBook value = response.readEntity(BtcBook.class);
			System.out.println(value);
		} else {
			System.err.println("error");
		}

		// while (true);
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		TableView table = new TableView();

		Scene scene = new Scene(new Group());
		stage.setTitle("Table View Sample");
		stage.setWidth(300);
		stage.setHeight(500);

		final Label label = new Label("Address Book");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);

		TableColumn firstNameCol = new TableColumn("First Name");
		TableColumn lastNameCol = new TableColumn("Last Name");
		TableColumn emailCol = new TableColumn("Email");

		table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}
}
