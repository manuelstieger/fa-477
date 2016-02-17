package ch.csbe.ifa.partei.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name="Kommission")
//@AttributeOverride(name="id", column=@Column(name="amtfk"))
@PrimaryKeyJoinColumn(name="amtfk", referencedColumnName="id")
public class Kommission extends Amt{

	@Column(nullable=false)
	private boolean stimmberechtigt;

	
	
	public Kommission(Ebene ebene, String bezeichnung, Date wahldatum, Date wahldauer, boolean stimmberechtigt) {
		super(ebene, bezeichnung, wahldatum, wahldauer);
		this.stimmberechtigt = stimmberechtigt;
	}

	public Kommission(boolean stimmberechtigt) {
		super();
		this.stimmberechtigt = stimmberechtigt;
	}

	public boolean isStimmberechtigt() {
		return stimmberechtigt;
	}

	public void setStimmberechtigt(boolean stimmberechtigt) {
		this.stimmberechtigt = stimmberechtigt;
	}
	
	
	
}
