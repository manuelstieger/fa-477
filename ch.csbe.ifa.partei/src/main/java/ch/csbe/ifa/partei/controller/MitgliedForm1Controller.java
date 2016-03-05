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
		
		if(view.name.getText().length()>0 && view.vorname.getText().length()>0
				&& view.wohnort.getValue() != null){
			if(Session.getInstance("first").getMap().get("mitglied") != null && Session.getInstance("first").getMap().get("mitglied") instanceof Mitglied){
				m = (Mitglied)Session.getInstance("first").getMap().get("mitglied");
				m.setName(view.name.getText());
				m.setVorname(view.vorname.getText());
				m.setOrt(view.wohnort.getValue());
				md.update(m);
				if(controller.tabpane.getSelectionModel().getSelectedIndex()>1)
					controller.tabpane.getTabs().remove(controller.tabpane.getSelectionModel().getSelectedIndex());
			}else{
				m = new Mitglied(view.name.getText(), view.vorname.getText(), view.wohnort.getValue());
				md.save(m);
			}
		
			view.name.setText("");
			view.vorname.setText("");
			view.wohnort.setValue(null);
			
			controller.mitglieder();
			controller.tabpane.getSelectionModel().select(0);
			
			Database.getInstance().closeSession();
	
			Session.getInstance("first").getMap().put("mitglied", m);
		
		}
	}
	
	public void cancel(){
		Mitglied2Controller controller = (Mitglied2Controller)view.iface;
		controller.tabpane.getTabs().remove(controller.tabpane.getSelectionModel().getSelectedIndex());
		controller.tabpane.getSelectionModel().select(0);
	}
	
}