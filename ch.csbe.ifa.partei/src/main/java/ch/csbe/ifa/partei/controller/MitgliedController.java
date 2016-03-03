package ch.csbe.ifa.partei.controller;

import java.io.IOException;
import java.util.List;

import ch.csbe.ifa.partei.App;
import ch.csbe.ifa.partei.MainApp;
import ch.csbe.ifa.partei.dao.Database;
import ch.csbe.ifa.partei.dao.MitgliedDao;
import ch.csbe.ifa.partei.dao.OrtDao;
import ch.csbe.ifa.partei.model.Mitglied;
import ch.csbe.ifa.partei.model.Ort;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

public class MitgliedController {
	public static Mitglied selectedmitglied;
	
	@FXML
	private Parent form; 
	
	@FXML
	public MitgliedFormController formController;

	@FXML
	public static TabPane tabpane;

	@FXML
	private TableView<Mitglied> mitgliedtable;
	@FXML
	private TableColumn<Mitglied, String> first;
	@FXML
	private TableColumn<Mitglied, String> last;
	@FXML
	private TableColumn<Mitglied, String> id;
	@FXML
	private TableColumn<Mitglied, String> wohnort;
	

	
	public void load(){
		formController.setData(mitgliedtable, first,last,id,wohnort);
	}
	

	@FXML
	public void initialize() throws IOException {
		//tabpane.getTabs().get(1).setContent(FXMLLoader.load(MainApp.class.getResource("view/mitglied-form.fxml")));
		load();
		
		
		
	}



	
}
