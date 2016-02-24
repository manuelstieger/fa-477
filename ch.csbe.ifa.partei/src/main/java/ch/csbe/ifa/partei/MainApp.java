package ch.csbe.ifa.partei;

import ch.csbe.ifa.partei.dao.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("view/mitglied.fxml"));
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);	
		primaryStage.setTitle("Mitglied Anwendung");
		primaryStage.show();
	}
	
	public void stop(){
		Database.getInstance().closeConnection();
	}

}
