package ch.csbe.ifa.partei.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name="Mitglied")
public class Mitglied {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String vorname;

	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Ort ort;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "MitgliedAmt", joinColumns = {
			@JoinColumn(name = "mitgliedfk", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "amtfk", nullable = false, updatable = false) })
	private List<Amt> aemter = new ArrayList<Amt>();

	public Mitglied() {
		super();
	}

	public Mitglied(String name, String vorname, Ort ort) {
		super();
		this.name = name;
		this.vorname = vorname;
		this.ort = ort;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public Ort getOrt() {
		return ort;
	}

	public void setOrt(Ort ort) {
		this.ort = ort;
	}

	public List<Amt> getAemter() {
		return aemter;
	}

	public void setAemter(List<Amt> aemter) {
		this.aemter = aemter;
	}

}
