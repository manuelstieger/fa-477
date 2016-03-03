package ch.csbe.ifa.partei.controller;

import java.io.IOException;
import java.util.List;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.util.Callback;
import javafx.util.StringConverter;

public class MitgliedFormController {

	private Tab mitgliederfassen;
	private Node content;
	
	private TableView<Mitglied> mitgliedtable;
	private TableColumn<Mitglied, String> first;
	private TableColumn<Mitglied, String> last;
	private TableColumn<Mitglied, String> id;
	private TableColumn<Mitglied, String> wohnort;
	private TableColumn<Mitglied, Image> edit;
	
	
	@FXML
	private TextField name;
	@FXML
	private TextField vorname;
	@FXML
	private ComboBox<Ort> ort;
	
	
	
	public void save() {
		if(MitgliedController.tabpane == null){
			MitgliedController.tabpane = (TabPane)name.getParent().getParent().getParent();
			this.mitgliedtable = (TableView<Mitglied>)MitgliedController.tabpane.lookup("#mitgliedtable");
			this.first = (TableColumn<Mitglied, String>)mitgliedtable.getColumns().get(1);
			this.last = (TableColumn<Mitglied, String>)mitgliedtable.getColumns().get(2);
			this.id = (TableColumn<Mitglied, String>)mitgliedtable.getColumns().get(0);
			this.wohnort = (TableColumn<Mitglied, String>)mitgliedtable.getColumns().get(3);
		}
		Database.getInstance().openSession();
		if(MitgliedController.selectedmitglied!=null){
			MitgliedController.selectedmitglied.setName(name.getText());
			MitgliedController.selectedmitglied.setVorname(vorname.getText());
			MitgliedController.selectedmitglied.setOrt(ort.getValue());
			Database.getInstance().getSession().update(MitgliedController.selectedmitglied);
			
			int seli = MitgliedController.tabpane.getSelectionModel().getSelectedIndex();
			Tab t = MitgliedController.tabpane.getTabs().get(seli);
			
			mitgliederfassen.setContent(content);
			
			((TextField)mitgliederfassen.getContent().lookup("#name")).setText("");
			((TextField)mitgliederfassen.getContent().lookup("#vorname")).setText("");
			((ComboBox<Ort>)mitgliederfassen.getContent().lookup("#ort")).getSelectionModel().select(null);
			
			MitgliedController.tabpane.getTabs().remove(1);
			MitgliedController.tabpane.getTabs().remove(t);
			MitgliedController.tabpane.getTabs().add(mitgliederfassen);
	
			
			MitgliedController.selectedmitglied = null;
		}else{
			Mitglied m = new Mitglied(name.getText(), vorname.getText(), ort.getValue());
			Database.getInstance().getSession().save(m);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Mitglied gespeichert");
			alert.setHeaderText("Mitglied information");
			alert.setContentText(m.getId() + " " + m.getOrt());
			alert.show();
		}
		Database.getInstance().getSession().flush();
		mitglieder();
		Database.getInstance().closeSession();


		MitgliedController.tabpane.getSelectionModel().select(0);
	}
	
	public void setData(TableView<Mitglied> tv, TableColumn<Mitglied, String> first,
			TableColumn<Mitglied, String> last, TableColumn<Mitglied, String> id, 
			TableColumn<Mitglied, String> wohnort){
		this.mitgliedtable = tv;
		this.first = first;
		this.last = last;
		this.id = id;
		this.wohnort = wohnort;
		

		Database.getInstance().openSession();
		orte();
		mitglieder();
		Database.getInstance().closeSession();
	}
	
	public void mitglieder() {
		MitgliedDao mitglieddao = new MitgliedDao();
		List<Mitglied> mitglieder = mitglieddao.list();
		first.setCellValueFactory(new PropertyValueFactory<Mitglied, String>("vorname"));
		last.setCellValueFactory(new PropertyValueFactory<Mitglied, String>("name"));
		id.setCellValueFactory(new PropertyValueFactory<Mitglied, String>("id"));
		wohnort.setCellValueFactory(new PropertyValueFactory<Mitglied, String>("ort"));
		mitgliedtable.getItems().setAll(mitglieder);

		mitgliedtable.setRowFactory(new Callback<TableView<Mitglied>, TableRow<Mitglied>>() {
			@Override
			public TableRow<Mitglied> call(TableView<Mitglied> tableView) {
				final TableRow<Mitglied> row = new TableRow<>();
				final ContextMenu contextMenu = new ContextMenu();
				final MenuItem removeMenuItem = new MenuItem("Remove");
				removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						Database.getInstance().openSession();
						mitglieddao.remove(row.getItem());
						mitgliedtable.getItems().remove(row.getItem());
					}
				});
				final MenuItem editMenuItem = new MenuItem("Edit");
				editMenuItem.setOnAction(new EventHandler<ActionEvent>() {

					@SuppressWarnings("unchecked")
					@Override
					public void handle(ActionEvent event) {
						mitgliederfassen = MitgliedController.tabpane.getTabs().get(1);
						content = mitgliederfassen.getContent();
						MitgliedController.selectedmitglied = row.getItem();
						Tab edit = new Tab("Edit " + row.getItem().getName());
						try {
							FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/mitglied-form.fxml"));

							edit.setContent(loader.load());
							((TextField)edit.getContent().lookup("#name")).setText(row.getItem().getName());
							((TextField)edit.getContent().lookup("#vorname")).setText(row.getItem().getVorname());
							((ComboBox<Ort>)edit.getContent().lookup("#ort")).getSelectionModel().select(row.getItem().getOrt());
							MitgliedController.tabpane.getTabs().add(edit);
							MitgliedController.tabpane.getSelectionModel().select(edit);
							loader.setController(this);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						//edit.setContent(content);
						
					}
				});

				contextMenu.getItems().add(editMenuItem);				
				contextMenu.getItems().add(removeMenuItem);
				// Set context menu on row, but use a binding to make it only
				// show for non-empty rows:
				row.contextMenuProperty()
						.bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));
				return row;
			}
		});

	}
	
	public void orte() {
		OrtDao ortdao = new OrtDao();
		List<Ort> orte = ortdao.list();
		ort.getItems().addAll(orte);
		
		ort.setConverter(new StringConverter<Ort>() {
		 
		  @Override public String toString(Ort object) { 
		  if(object == null){ return null; } return
		  object.getPlz() + " " + object.getOrt(); }
		  
		  @Override public Ort fromString(String string) {
			  System.out.println(string);
			  OrtDao od = new OrtDao();
			  String plz = string.split(" ")[0];
			  String ort = string.split(" ")[1];
			  Ort o = od.search(plz, ort);
			  if(o != null){
				  System.out.println("Vorhanden Ort");
				  return o;
			  }
			  System.out.println("Nicht vorhanden ort");
			  o = new Ort(plz, ort);
			  int id = od.save(o);
			  System.out.println(id);
			  return o;
			 } 
		  });
		 
	}
	
	@FXML public void initialize(){
	}


}
