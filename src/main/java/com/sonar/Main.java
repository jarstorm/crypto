package com.sonar;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Logger;

import com.sonar.broker.BitsoBroker;
import com.sonar.broker.SonarBroker;
import com.sonar.state.SonarState;

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
	
	public static void main(String[] args) throws URISyntaxException, KeyManagementException, NoSuchAlgorithmException {
	
		List<SonarBroker> brokers = new ArrayList<>();
		brokers.add(new BitsoBroker());
		
		// Create state object
		SonarState sonarState = new SonarState(brokers);		

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
