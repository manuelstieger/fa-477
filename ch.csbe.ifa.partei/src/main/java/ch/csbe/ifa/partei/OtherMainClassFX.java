package ch.csbe.ifa.partei;

import ch.csbe.ifa.partei.dao.Database;
import ch.csbe.ifa.partei.helper.Session;
import ch.csbe.ifa.partei.helper.UiFxml;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OtherMainClassFX extends Application {

	public static void main(String[] args) {
		Session.getInstance("first");

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		/* Scene scene = new Scene(new MitgliedForm()); */
		Scene scene = new Scene(UiFxml.load("view/mitglied2.fxml").load());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Parteiverwaltung");
		primaryStage.show();
	}

	public void stop() {
		Database.getInstance().closeConnection();
	}

}
