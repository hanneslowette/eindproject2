package org.betavzw.view.io;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.betavzw.entity.VerlofAanvraag;
import org.betavzw.entity.Werknemer;
import org.betavzw.util.Filter;
import org.betavzw.util.Toestand;
import org.betavzw.view.View;
import org.betavzw.view.bean.Bean;
import org.hibernate.validator.constraints.NotEmpty;

@Named("verlofAanvraag")
@SessionScoped
public class VerlofAanvraagIO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Bean<VerlofAanvraag> bean;

	@Pattern(regexp = "[A-Z][a-zA-Z .,_-]*", message = "Geldige voornaam ingeven")
	@NotEmpty(message = "Veld voornaam moet ingevuld zijn")
	private String voornaam;
	@Pattern(regexp = "[A-Z][a-zA-Z .,_-]*", message = "Geldige naam ingeven")
	@NotEmpty(message = "Veld naam moet ingevuld zijn")
	private String naam;
	@Digits(integer = 3, fraction = 0, message = "Geldig personeelsnummer ingeven")
	private int personeelsNr;
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Veld startdatum moet ingevuld zijn")
	private Date startDatum;
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Veld einddatum moet ingevuld zijn")
	private Date eindDatum;

	/**
	 * verstuurfunctie voor commandButton van verlofaanvragen.xhtml waar een
	 * nieuwe verlofaanvraag wordt aangemaakt in de databank
	 */
	public String verstuur() {
		if (startDatum.before(eindDatum)) {
			VerlofAanvraag verlofAanvraag = new VerlofAanvraag();
			verlofAanvraag.setStartDatum(startDatum.toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate());
			verlofAanvraag.setEindDatum(eindDatum.toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate());
			verlofAanvraag.setAanvraagDatum(LocalDate.now());
			verlofAanvraag.setToestand(Toestand.PENDING);
			verlofAanvraag.setWerknemer(new Werknemer(voornaam, naam));
			bean.offer(verlofAanvraag);
			return View.VERSTUURD;
		} else {
			return View.VERLOFAANVRAGEN;
		}
	}

	/**
	 * checkfunctie voor commandButton van verlofaanvragen.xhtml waarmee de
	 * werknemer een pagina te zien krijgt waar de status van de verlofaanvraag
	 * staat
	 */
	public String check() {
		VerlofAanvraag aanvraag = bean.getSingle(new Filter("id", id));

		String pagina = null;
		switch (aanvraag.getToestand()) {
		case PENDING:
			pagina = View.PENDING;
			break;
		case ACCEPTED:
			pagina = View.ACCEPTED;
			break;
		case REJECTED:
		case CANCELED:
			pagina = View.REJECTED;
			break;
		}
		return pagina;
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

}
