package ch.csbe.ifa.partei.dao;

import java.util.List;

import org.hibernate.Query;

import ch.csbe.ifa.partei.model.Ort;

public class OrtDao {
	
	@SuppressWarnings("unchecked")
	public List<Ort> list(){
		Query query = Database.getInstance().getSession().createQuery("from Ort");

		return query.list();
	}

}
