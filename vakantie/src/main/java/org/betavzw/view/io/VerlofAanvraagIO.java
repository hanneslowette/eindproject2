package org.betavzw.view.io;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
import org.betavzw.util.exceptions.GeboortedatumInDeToekomstException;
import org.betavzw.view.View;
import org.betavzw.view.bean.Bean;
import org.hibernate.validator.constraints.NotEmpty;

@Named("verlofAanvraag")
@SessionScoped
public class VerlofAanvraagIO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Bean<VerlofAanvraag> verlofAanvraag_bean;
	@Inject
	private Bean<Werknemer> werknemer_bean;

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
	private LocalDate aanvraagDatum = LocalDate.now();

	public VerlofAanvraagIO() {
		super();
	}

	/**
	 * verstuurfunctie voor commandButton van verlofaanvragen.xhtml waar een
	 * nieuwe verlofaanvraag wordt aangemaakt in de databank
	 * 
	 * @throws GeboortedatumInDeToekomstException
	 */
	public String verstuur() throws GeboortedatumInDeToekomstException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (eindDatum.before(startDatum)) {
			facesContext.addMessage("", new FacesMessage(
					"De startdatum moet voor de einddatum liggen"));
			return View.VERLOFAANVRAGEN;
		}
		if (isOpTijdAangevraagd()) {
			facesContext.addMessage("", new FacesMessage(
					"Het verlof moet 14 dagen op voorhand aangevraagd worden"));
			return View.VERLOFAANVRAGEN;
		}
		if (isNietOverlappend()) {
			facesContext
					.addMessage(
							"",
							new FacesMessage(
									"De verlofaanvraag mag niet overlappen met een andere geannuleerde of afgekeurde verlofaanvraag"));
			return View.VERLOFAANVRAGEN;
		}
		if (isGenoegVerlofdagen()) {
			facesContext
					.addMessage(
							"",
							new FacesMessage(
									"Er zijn niet genoeg verlofdagen om nog een verlofaanvraag te kunnen maken"));
			return View.VERLOFAANVRAGEN;
		} else {
			VerlofAanvraag verlofAanvraag = new VerlofAanvraag();
			verlofAanvraag.setStartDatum(startDatum.toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate());
			verlofAanvraag.setEindDatum(eindDatum.toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDate());
			verlofAanvraag.setAanvraagDatum(aanvraagDatum);
			verlofAanvraag.setToestand(Toestand.PENDING);
			Werknemer werknemer = werknemer_bean.getSingle(new Filter(
					"personeelsNr", personeelsNr));
			verlofAanvraag.setWerknemer(new Werknemer(werknemer.getVoornaam(),
					werknemer.getNaam(), werknemer.getEmail(), werknemer
							.getGeboortedatum(), werknemer.getAdres()));
			verlofAanvraag_bean.offer(verlofAanvraag);
			return View.VERSTUURD;
		}
	}

	/**
	 * functie die true weergeeft als het verlof op tijd is aangevraagd nl. 14
	 * dagen op voorhand
	 */
	public boolean isOpTijdAangevraagd() {
		LocalDate start = startDatum.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		return Period.between(start, aanvraagDatum).getDays() < 14;
	}

	/**
	 * functie die true weergeeft als er geen overlappende verlofaanvraag is die
	 * al geannuleerd of afgekeurd is
	 */
	public boolean isNietOverlappend() {
		// start1.before(end2) && start2.before(end1);
		LocalDate startDatumtmp = startDatum.toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate eindDatumtmp = eindDatum.toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDate();
		List<LocalDate> startdatums = new ArrayList<LocalDate>();
		List<LocalDate> einddatums = new ArrayList<LocalDate>();

		// return startDatum.before(when) && .before(eindDatum) ? true : false;
		return false;
	}

	/**
	 * functie die true weergeeft als de werknemer nog genoeg verlofdagen heeft
	 */
	public boolean isGenoegVerlofdagen() {
		// TODO: genoeg verlofdagen halen uit datatabel
		return false;
	}

	public LocalDate getAanvraagDatum() {
		return aanvraagDatum;
	}

	public void setAanvraagDatum(LocalDate aanvraagDatum) {
		this.aanvraagDatum = aanvraagDatum;
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
