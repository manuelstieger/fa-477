package ch.csbe.ifa.partei.controller;

import java.util.List;

import ch.csbe.ifa.partei.dao.Database;
import ch.csbe.ifa.partei.dao.OrtDao;
import ch.csbe.ifa.partei.model.Ort;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.StringConverter;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class MitgliedController {
	
	@FXML
	private TextField name;
	@FXML
	private TextField vorname;
	@FXML
	private ComboBox<Ort> ort;
	
	
	public void save(){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Eingegebene Mitglieddaten");
		alert.setHeaderText("Mitglied information");
		alert.setContentText(name.getText() + "\n" + ort.getValue());
		alert.show();
	}
	
	@FXML
	public void initialize(){
		Database.getInstance().openSession();
		OrtDao ortdao = new OrtDao();
		List<Ort> orte = ortdao.list();
		ort.getItems().addAll(orte);
		/*ort.setConverter(new StringConverter<Ort>() {
			
			@Override
			public String toString(Ort object) {
				// TODO Auto-generated method stub
				if(object == null){
					return null;
				}
				return object.getOrt();
			}
			
			@Override
			public Ort fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});*/
		Database.getInstance().closeSession();
	}

}
