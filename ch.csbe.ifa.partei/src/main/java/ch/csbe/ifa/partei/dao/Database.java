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
	private Properties properties;
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
		this.properties = new Properties();
    	this.properties.load(App.class.getResourceAsStream("/hibernate.properties"));
		
		this.config = new Configuration()
	    .addAnnotatedClass(Ort.class)
	    .addAnnotatedClass(Mitglied.class)
	    .addAnnotatedClass(Amt.class)
	    .addAnnotatedClass(Politik.class)
	    .addAnnotatedClass(Kommission.class)
	    .setProperty("hibernate.dialect", properties.getProperty("dialect"))
	    .setProperty("hibernate.connection.url", properties.getProperty("url"))
	    .setProperty("hibernate.connection.username", properties.getProperty("user"))
	    .setProperty("hibernate.connection.password", properties.getProperty("password"))
	    .setProperty("hibernate.hbm2ddl.auto", properties.getProperty("strategy")) //validate, create, create-drop, update
	    .setProperty("hibernate.order_updates", "true");
		
		this.sessions = this.config.buildSessionFactory();
	}
	
	public void openSession(){
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
