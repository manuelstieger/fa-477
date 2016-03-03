package ch.csbe.ifa.partei;

import ch.csbe.ifa.partei.dao.Database;
import ch.csbe.ifa.partei.helper.Session;
import ch.csbe.ifa.partei.helper.UiFxml;
import ch.csbe.ifa.partei.view.MitgliedForm;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OtherMainClassFX extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session.getInstance("first");
		
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		/*Scene scene = new Scene(new MitgliedForm());*/
		Scene scene = new Scene(UiFxml.load("view/mitglied2.fxml").load());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Parteiverwaltung");
		primaryStage.show();
	}
	
	public void stop(){
		Database.getInstance().closeConnection();
	}

}
