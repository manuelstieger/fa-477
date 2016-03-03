package ch.csbe.ifa.partei.controller;

import ch.csbe.ifa.partei.dao.Database;
import ch.csbe.ifa.partei.dao.MitgliedDao;
import ch.csbe.ifa.partei.helper.Session;
import ch.csbe.ifa.partei.model.Mitglied;
import ch.csbe.ifa.partei.view.MitgliedForm;


public class MitgliedForm1Controller extends Controller {
	
	private MitgliedForm view;
	
	
	public MitgliedForm1Controller(MitgliedForm view){
		this.view = view;
	}

	public void save(){
		Mitglied m;
		MitgliedDao md = new MitgliedDao();
		Mitglied2Controller controller = (Mitglied2Controller)view.iface;
		Database.getInstance().openSession();
		if(Session.getInstance("first").getMap().get("mitglied") != null && Session.getInstance("first").getMap().get("mitglied") instanceof Mitglied){
			m = (Mitglied)Session.getInstance("first").getMap().get("mitglied");
			m.setName(view.name.getText());
			m.setVorname(view.vorname.getText());
			m.setOrt(view.wohnort.getValue());
			md.update(m);
			controller.tabpane.getTabs().remove(controller.tabpane.getSelectionModel().getSelectedIndex());
		}else{
			m = new Mitglied(view.name.getText(), view.vorname.getText(), view.wohnort.getValue());
			md.save(m);
		}
		
		controller.mitglieder();
		controller.tabpane.getSelectionModel().select(0);
		
		Database.getInstance().closeSession();

		Session.getInstance("first").getMap().put("mitglied", m);
	}
	
}