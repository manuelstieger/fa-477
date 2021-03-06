package ch.csbe.ifa.bidirektional.model;

public class Auto {

	private Person person;

	public Auto() {
		super();
	}

	public Auto(Person person) {
		super();
		this.setPerson(person);
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		if(this.person != person){
			if(this.person != null)
				this.person.setAuto(null);
			this.person = person;
			this.person.setAuto(this);
		}
	}

}
