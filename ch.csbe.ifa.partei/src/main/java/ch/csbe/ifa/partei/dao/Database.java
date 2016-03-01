package ch.csbe.ifa.partei.dao;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ch.csbe.ifa.partei.App;
import ch.csbe.ifa.partei.model.Amt;
import ch.csbe.ifa.partei.model.Kommission;
import ch.csbe.ifa.partei.model.Mitglied;
import ch.csbe.ifa.partei.model.Ort;
import ch.csbe.ifa.partei.model.Politik;

public class Database {
	
	private static Database instance;
	private Configuration config;
	private SessionFactory sessions;
	private Session session;
	
	public static Database getInstance(){
		if(instance == null){
			try {
				instance = new Database();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	private Database() throws IOException{
		this.config = new Configuration()
	    .addAnnotatedClass(Ort.class)
	    .addAnnotatedClass(Mitglied.class)
	    .addAnnotatedClass(Amt.class)
	    .addAnnotatedClass(Politik.class)
	    .addAnnotatedClass(Kommission.class);
	
		this.sessions = this.config.buildSessionFactory();
	}
	
	public void openSession(){
		if(this.sessions == null || this.sessions.isClosed()){
			this.sessions = this.config.buildSessionFactory();
		}
		if(this.session == null || !this.session.isOpen())
			this.session = this.sessions.openSession();
	}
	
	public Session getSession(){
		return this.session;
	}
	
	public void closeSession(){
		this.session.close();
	}
	
	public void closeConnection(){
		if(this.session.isOpen()){
			this.session.close();
		}
		this.sessions.close();
	}

	
	
}
