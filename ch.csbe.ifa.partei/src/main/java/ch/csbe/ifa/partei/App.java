package ch.csbe.ifa.partei;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ch.csbe.ifa.partei.model.Amt;
import ch.csbe.ifa.partei.model.Kommission;
import ch.csbe.ifa.partei.model.Mitglied;
import ch.csbe.ifa.partei.model.Ort;
import ch.csbe.ifa.partei.model.Politik;

/**
 * Hello world!
 *
 */
public class App 
{
	static Logger log = Logger.getLogger("ifa");
	
    public static void main( String[] args ) throws IOException
    {    	
    	log.info("Application starts");
    	
    	Properties properties = new Properties();
    	properties.load(App.class.getResourceAsStream("/hibernate.properties"));
    	
    	Configuration cfg = new Configuration()
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
    	
    	
    	SessionFactory sessions;
    	Session session;
    	sessions = cfg.buildSessionFactory();
    	session = sessions.openSession(); // open a new Session
    	
    	try{
	    	Ort o = new Ort("2552", "Orpund");
	    	session.save(o);
	    	
	    	Mitglied person = new Mitglied("Muster","Max", o);
	    	
	    	Politik politik = new Politik(Amt.Ebene.GEMEINDE, "Gemeinderat", new Date(), null, 10000);
	    	Kommission kommission = new Kommission(Amt.Ebene.KANTON, "Steuerkommission", new Date(), null, true);
	    	
	    	person.getAemter().add(politik);
	    	person.getAemter().add(kommission);
	    	politik.getMitglieder().add(person);
	    	kommission.getMitglieder().add(person);
	    	
	    	session.save(person);
	    	session.flush();
	    	
	    	
	    	/*Ort ort = new Ort();
    		ort = session.get(ort.getClass(),1);
    		
    		System.out.println(ort.getPlz() + " " + ort.getOrt());*/
	    	Mitglied p = new Mitglied();
	    	p = session.get(p.getClass(), 1);
	    	
	    	System.out.println(p.getName());
	    	for(Amt a : p.getAemter()){
	    		System.out.println(a.getBezeichnung());
	    	}
        	
    	}catch(Exception e){
    		System.out.println(e.getMessage());
    	}finally{ 	
    		session.disconnect();
        	session.close();   
        	sessions.close();
    	}
    	
    	
    	log.info("Application ends");
    }
}
