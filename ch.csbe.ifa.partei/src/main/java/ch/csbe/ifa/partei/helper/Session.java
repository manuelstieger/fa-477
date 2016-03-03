package ch.csbe.ifa.partei.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Session {
	private static Map<String,Session> instances;
	private Map<String, Object> elements = new HashMap<String, Object>();
	
	public static Session getInstance(String sessionid){
		if(instances == null){
			instances = new HashMap<String, Session>();
		}
		if(!instances.containsKey(sessionid)){
			instances.put(sessionid, new Session());
		}
		return instances.get(sessionid);
	}
	
	public Map<String, Object> getMap(){
		return elements;
	}
	
	
	private Session(){
		
	}
	
}
