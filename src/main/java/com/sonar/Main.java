package com.sonar;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sonar.bean.BtcBook;
import com.sonar.websocket.WebsocketClientEndpoint;

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
	
	private static final String WEBSOCKET_URI = "wss://ws.bitso.com";
	private static final String REST_URI = "https://api.bitso.com/v3/order_book/?book=btc_mxn";

	private static final ScheduledExecutorService scheduler =
		     Executors.newScheduledThreadPool(1);
	
	public static void main(String[] args) throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException {
		
		WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(WEBSOCKET_URI));

		final Client client = ClientBuilder.newClient();

		scheduler.scheduleAtFixedRate(new Runnable() {
		    @Override
		    public void run() {
		        System.out.println("scheduleAtFixedRate:    " + new Date());
		    }
		}, 1, 3L , TimeUnit.SECONDS);
		
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
