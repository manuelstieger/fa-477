package ch.csbe.ifa.partei.dao;

import java.util.List;

import org.hibernate.Query;

import ch.csbe.ifa.partei.model.Mitglied;

public class MitgliedDao {
	@SuppressWarnings("unchecked")
	public List<Mitglied> list(){
		Query query = Database.getInstance().getSession().createQuery("from Mitglied");

		return query.list();
	}
	
	public void remove(Mitglied m){
		Database.getInstance().getSession().delete(m);
		Database.getInstance().getSession().flush();
	}
	
	public int save(Mitglied m){
		int id = (Integer) Database.getInstance().getSession().save(m);
		Database.getInstance().getSession().flush();
		return id;
	}
	
	
	public void update(Mitglied m){
		Database.getInstance().getSession().update(m);
		Database.getInstance().getSession().flush();
	}
}
