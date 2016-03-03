package ch.csbe.ifa.partei.helper;

import java.io.IOException;

import ch.csbe.ifa.partei.OtherMainClassFX;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

public class UiFxml {

	public static FXMLLoader load(Pane parent, String path) throws IOException{
		FXMLLoader loader = new FXMLLoader(OtherMainClassFX.class.getResource(path));
		parent.getChildren().add(loader.load());
		return loader;
	}
	
	public static FXMLLoader load(String path) throws IOException{
		FXMLLoader loader = new FXMLLoader(OtherMainClassFX.class.getResource(path));
		return loader;
	}
	
	
}
