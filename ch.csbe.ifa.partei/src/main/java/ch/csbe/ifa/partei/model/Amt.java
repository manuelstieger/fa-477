package ch.csbe.ifa.partei.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

@Entity(name="Amt")
@Inheritance(strategy=InheritanceType.JOINED)
public class Amt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	@Column(nullable = false)
	protected Ebene ebene;
	@Column(length = 32, nullable = false)
	protected String bezeichnung;
	@Column(nullable = false)
	protected Date wahldatum;
	@Column(nullable = true)
	protected Date wahldauer;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "aemter")
	protected List<Mitglied> mitglieder = new ArrayList<Mitglied>();

	public Amt() {
		super();
	}

	public Amt(Ebene ebene, String bezeichnung, Date wahldatum, Date wahldauer) {
		super();
		this.ebene = ebene;
		this.bezeichnung = bezeichnung;
		this.wahldatum = wahldatum;
		this.wahldauer = wahldauer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ebene getEbene() {
		return ebene;
	}

	public void setEbene(Ebene ebene) {
		this.ebene = ebene;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public Date getWahldatum() {
		return wahldatum;
	}

	public void setWahldatum(Date wahldatum) {
		this.wahldatum = wahldatum;
	}

	public Date getWahldauer() {
		return wahldauer;
	}

	public void setWahldauer(Date wahldauer) {
		this.wahldauer = wahldauer;
	}

	public List<Mitglied> getMitglieder() {
		return mitglieder;
	}

	public void setMitglieder(List<Mitglied> mitglieder) {
		this.mitglieder = mitglieder;
	}

	public enum Ebene {
		GEMEINDE, KANTON, BUND
	}

}
