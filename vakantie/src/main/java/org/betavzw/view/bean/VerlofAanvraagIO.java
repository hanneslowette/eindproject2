package org.betavzw.view.bean;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.betavzw.ejb.VerlofAanvraagEJB;
import org.betavzw.util.Toestand;

@Named("verlofAanvraag")
@SessionScoped
public class VerlofAanvraagIO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private VerlofAanvraagEJB verlofAanvraagEJB;
	private String voornaam;
	private String naam;
	private int personeelsNr;
	private Date startDatum;
	private Date eindDatum;

	public String getVoornaam() {	
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public int getPersoneelsNr() {
		return personeelsNr;
	}

	public void setPersoneelsNr(int personeelsNr) {
		this.personeelsNr = personeelsNr;
	}

	public Date getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(Date startDatum) {
		this.startDatum = startDatum;
	}

	public Date getEindDatum() {
		return eindDatum;
	}

	public void setEindDatum(Date eindDatum) {
		this.eindDatum = eindDatum;
	}

	/**
	 * verstuurfunctie voor commandButton van verlofaanvragen.xhtml waarmee de
	 * response pagina bepaald wordt via het enum Toestand(PENDING, ACCEPTED,
	 * REJECTED, CANCELED)
	 */
	public String verstuur() {
		String pagina = "";
		if (startDatum.before(eindDatum)) {
			// TODO: zet message dat startdatum voor einddatum moet komen
			pagina = "verlofaanvraag";
			if (verlofAanvraagEJB.getToestand() == Toestand.PENDING) {
				pagina = "verlofpending";
			} else if (verlofAanvraagEJB.getToestand() == Toestand.ACCEPTED) {
				pagina = "verlofgoedgekeurd";
			} else if (verlofAanvraagEJB.getToestand() == Toestand.REJECTED) {
				pagina = "verlofafgekeurd";
			}
		}
		return pagina;
	}
}
