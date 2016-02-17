package ch.csbe.ifa.partei.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Ort {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(length=4,nullable=false)
	private String plz;

	@Column(length=32,nullable=false)
	private String ort;
	
	@Transient
	private String nichtspeichern;

	public Ort() {
		super();
	}

	public Ort(String plz, String ort) {
		super();
		this.plz = plz;
		this.ort = ort;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

}
