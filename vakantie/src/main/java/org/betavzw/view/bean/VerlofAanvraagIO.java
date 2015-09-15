package org.betavzw.view.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.betavzw.ejb.VerlofAanvraagEJB;
import org.betavzw.entities.Werknemer;
import org.betavzw.util.Toestand;
import org.hibernate.validator.constraints.NotEmpty;

@Named("verlofAanvraag")
@SessionScoped
public class VerlofAanvraagIO implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private VerlofAanvraagEJB verlofAanvraagEJB;
	@Pattern(regexp = "[A-Z][a-zA-Z .,_-]*")
	@NotEmpty
	@NotNull
	private String voornaam;
	@Pattern(regexp = "[A-Z][a-zA-Z .,_-]*")
	@NotEmpty
	@NotNull
	private String naam;
	@Digits(integer = 4, fraction = 0)
	@NotEmpty
	@NotNull
	private int personeelsNr;
	@Temporal(TemporalType.DATE)
	@NotEmpty
	@NotNull
	private Date startDatum;
	@Temporal(TemporalType.DATE)
	@NotEmpty
	@NotNull
	private Date eindDatum;
	@Digits(integer = 10, fraction = 0)
	@NotEmpty
	@NotNull
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	 * verstuurfunctie voor commandButton van verlofaanvragen.xhtml waar een
	 * nieuwe verlofaanvraag wordt aangemaakt in de databank
	 */
	public String verstuur() {
		if (startDatum.before(eindDatum)) {
			verlofAanvraagEJB.commit(verlofAanvraagEJB.aanmaken(startDatum
					.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
					eindDatum.toInstant().atZone(ZoneId.systemDefault())
							.toLocalDate(), LocalDate.now(), Toestand.PENDING,
					new Werknemer(voornaam, naam, personeelsNr)));
			return "verlofaanvraagverstuurd";
		} else {
			// TODO: zet message dat startdatum voor einddatum moet komen
			return "verlofaanvragen";
		}
	}

	/**
	 * checkfunctie voor commandButton van verlofaanvragen.xhtml waarmee de
	 * werknemer een pagina te zien krijgt waar de status van de verlofaanvraag
	 * staat
	 */
	public String check() {
		String pagina = "";
		if (verlofAanvraagEJB.getVerlofAanvraagId(id).getToestand() == Toestand.PENDING) {
			pagina = "verlofpending";
		} else if (verlofAanvraagEJB.getVerlofAanvraagId(id).getToestand() == Toestand.ACCEPTED) {
			pagina = "verlofgoedgekeurd";
		} else if (verlofAanvraagEJB.getVerlofAanvraagId(id).getToestand() == Toestand.REJECTED) {
			pagina = "verlofafgekeurd";
		}
		return pagina;
	}

}
