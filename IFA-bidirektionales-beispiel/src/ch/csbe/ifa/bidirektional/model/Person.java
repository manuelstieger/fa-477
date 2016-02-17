package ch.csbe.ifa.bidirektional.model;

public class Person {

	private Auto auto;
	
	public Person(){
		
	}
	
	public Person(Auto auto){
		this.setAuto(auto);
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		if(this.auto!=auto){
			this.auto = auto;
			if(auto != null)
				this.auto.setPerson(this);
		}
	}
	
	
	
}
