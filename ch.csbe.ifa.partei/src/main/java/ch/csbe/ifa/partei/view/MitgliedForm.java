package ch.csbe.ifa.partei.view;

import java.util.List;

import ch.csbe.ifa.partei.controller.Controller;
import ch.csbe.ifa.partei.controller.MitgliedForm1Controller;
import ch.csbe.ifa.partei.dao.Database;
import ch.csbe.ifa.partei.dao.OrtDao;
import ch.csbe.ifa.partei.helper.Session;
import ch.csbe.ifa.partei.model.Mitglied;
import ch.csbe.ifa.partei.model.Ort;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

public class MitgliedForm extends AnchorPane {

	MitgliedForm instance;

	Label lname = new Label("Name");
	Label lvorname = new Label("Vorname");
	Label lwohnort = new Label("Wohnort");
	public TextField name = new TextField();
	public TextField vorname = new TextField();
	public ComboBox<Ort> wohnort = new ComboBox<Ort>();
	Button save = new Button("Speichern");
	Button cancel = new Button("Abbrechen");
	public Controller iface;

	public MitgliedForm(Controller iface) {
		this.iface = iface;
		this.setPrefHeight(225);
		this.setPrefWidth(516);

		lname.setLayoutX(138);
		lname.setLayoutY(41);

		lvorname.setLayoutX(117);
		lvorname.setLayoutY(79);

		lwohnort.setLayoutX(118);
		lwohnort.setLayoutY(121);

		name.setLayoutX(203);
		name.setLayoutY(36);
		name.setPrefHeight(25);
		name.setPrefWidth(215);
		name.setId("name");

		vorname.setLayoutX(203);
		vorname.setLayoutY(74);
		vorname.setPrefHeight(25);
		vorname.setPrefWidth(215);
		vorname.setId("vorname");

		wohnort.setLayoutX(203);
		wohnort.setLayoutY(116);
		wohnort.setPrefHeight(25);
		wohnort.setPrefWidth(215);
		wohnort.setId("wohnort");
		wohnort.setEditable(true);

		save.setLayoutX(334);
		save.setLayoutY(158);

		save.addEventHandler(ActionEvent.ANY, new SaveHandler());
		

		
		cancel.setLayoutX(334);
		cancel.setLayoutY(188);
		

		cancel.addEventHandler(ActionEvent.ANY, new CancelHandler());

		this.getChildren().addAll(lvorname, lname, lwohnort, name, vorname, wohnort, save, cancel);

		Database.getInstance().openSession();
		orte(null);
		Database.getInstance().closeSession();
		fillData();
		instance = this;
	}

	public void orte(Ort selected) {
		OrtDao ortdao = new OrtDao();
		List<Ort> orte = ortdao.list();
		wohnort.getItems().addAll(orte);

		wohnort.setConverter(new StringConverter<Ort>() {

			@Override
			public String toString(Ort object) {
				if (object == null) {
					return null;
				}
				return object.getPlz() + " " + object.getOrt();
			}

			@Override
			public Ort fromString(String string) {
				Database.getInstance().openSession();
				OrtDao od = new OrtDao();
				String plz = string.split(" ")[0];
				String ort = string.split(" ")[1];
				Ort o = od.search(plz, ort);
				if (o != null) {
					return o;
				}
				o = new Ort(plz, ort);
				od.save(o);
				wohnort.getItems().add(o);
				Database.getInstance().closeSession();
				return o;
			}
		});
		if (selected != null) {
			wohnort.getSelectionModel().select(selected);
		}
	}

	public void clearData() {
		name.setText("");
		vorname.setText("");
		wohnort.getSelectionModel().select(null);
	}

	public void fillData() {
		if (Session.getInstance("first").getMap().get("mitglied") != null
				&& Session.getInstance("first").getMap().get("mitglied") instanceof Mitglied) {
			Mitglied m = (Mitglied) Session.getInstance("first").getMap().get("mitglied");
			name.setText(m.getName());
			vorname.setText(m.getVorname());
			wohnort.getSelectionModel().select(m.getOrt());
		}
	}

	public class SaveHandler implements EventHandler<Event> {

		public void handle(Event event) {
			MitgliedForm1Controller mc = new MitgliedForm1Controller(instance);
			mc.save();

		}

	}
	
	public class CancelHandler implements EventHandler<Event> {

		public void handle(Event event) {
			MitgliedForm1Controller mc = new MitgliedForm1Controller(instance);
			mc.cancel();

		}

	}

}
