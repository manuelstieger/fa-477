package ch.csbe.ifa.partei.dao;

import java.util.List;

import org.hibernate.Query;

import ch.csbe.ifa.partei.model.Ort;

public class OrtDao {
	
	@SuppressWarnings("unchecked")
	public List<Ort> list(){
		Query query = Database.getInstance().getSession().createQuery("from Ort order by ort");

		return query.list();
	}
	
	public Ort search(String plz, String ort){
		Query query = Database.getInstance().getSession().createQuery("from Ort where plz = :plz and ort = :ort");
		query.setString("plz",plz);
		query.setString("ort", ort);
		return (Ort) query.uniqueResult();
	}
	
	public int save(Ort ort){
		int id = (Integer)Database.getInstance().getSession().save(ort);
		Database.getInstance().getSession().flush();
		return id;
	}

}
