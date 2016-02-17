package ch.csbe.ifa.partei.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name="Politik")
@PrimaryKeyJoinColumn(name="amtfk", referencedColumnName="id")
public class Politik extends Amt {
	
	@Column(nullable=false)
	private float gehalt;

	public Politik(float gehalt) {
		super();
		this.gehalt = gehalt;
	}

	public Politik(Ebene ebene, String bezeichnung, Date wahldatum, Date wahldauer, float gehalt) {
		super(ebene, bezeichnung, wahldatum, wahldauer);
		this.gehalt = gehalt;
	}

	public float getGehalt() {
		return gehalt;
	}

	public void setGehalt(float gehalt) {
		this.gehalt = gehalt;
	}

}
