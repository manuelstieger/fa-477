package ch.csbe.ifa.partei.controller;

import java.util.List;

import ch.csbe.ifa.partei.dao.Database;
import ch.csbe.ifa.partei.dao.MitgliedDao;
import ch.csbe.ifa.partei.helper.Session;
import ch.csbe.ifa.partei.model.Mitglied;
import ch.csbe.ifa.partei.view.MitgliedForm;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class Mitglied2Controller extends Controller {

	@FXML
	public TabPane tabpane;
	@FXML
	private Tab erfassen;
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

	private Controller iface;

	@FXML
	public void initialize() {
		iface = this;
		erfassen.setContent(new MitgliedForm(iface));
		Database.getInstance().openSession();
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

					@Override
					public void handle(ActionEvent event) {
						Session.getInstance("first").getMap().put("mitglied", row.getItem());
						Tab t = new Tab("Bearbeiten " + row.getItem().getName());
						t.setContent(new MitgliedForm(iface));
						tabpane.getTabs().add(t);
						tabpane.getSelectionModel().select(tabpane.getTabs().size() - 1);
					}
				});

				contextMenu.getItems().add(editMenuItem);
				contextMenu.getItems().add(removeMenuItem);
				row.contextMenuProperty()
						.bind(Bindings.when(row.emptyProperty()).then((ContextMenu) null).otherwise(contextMenu));
				return row;
			}
		});

	}

}
