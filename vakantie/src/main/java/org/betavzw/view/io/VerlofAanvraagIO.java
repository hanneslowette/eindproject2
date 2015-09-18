package org.betavzw.view.io;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

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
	private LocalDate aanvraagDatum = LocalDate.now();

	/**
	 * verstuurfunctie voor commandButton van verlofaanvragen.xhtml waar een
	 * nieuwe verlofaanvraag wordt aangemaakt in de databank
	 */
	public String verstuur() {
		if (startDatum.before(eindDatum)) {
			if (isOpTijdAangevraagd() == true) {
				if (isNietOverlappend() == true) {
					if (isGenoegVerlofdagen() == true) {
						VerlofAanvraag verlofAanvraag = new VerlofAanvraag();
						verlofAanvraag.setStartDatum(startDatum.toInstant()
								.atZone(ZoneId.systemDefault()).toLocalDate());
						verlofAanvraag.setEindDatum(eindDatum.toInstant()
								.atZone(ZoneId.systemDefault()).toLocalDate());
						verlofAanvraag.setAanvraagDatum(aanvraagDatum);
						verlofAanvraag.setToestand(Toestand.PENDING);
						verlofAanvraag.setWerknemer(new Werknemer(voornaam,
								naam));
						bean.offer(verlofAanvraag);
						return View.VERSTUURD;
					} else {
						FacesContext facesContext = FacesContext
								.getCurrentInstance();
						facesContext
								.addMessage(
										"",
										new FacesMessage(
												"Er zijn niet genoeg verlofdagen om nog een verlofaanvraag te kunnen maken"));
						return View.VERLOFAANVRAGEN;
					}
				} else {
					FacesContext facesContext = FacesContext
							.getCurrentInstance();
					facesContext
							.addMessage(
									"",
									new FacesMessage(
											"De verlofaanvraag mag niet overlappen met een andere geannuleerde of afgekeurde verlofaanvraag"));
					return View.VERLOFAANVRAGEN;
				}
			} else {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext
						.addMessage(
								"",
								new FacesMessage(
										"Het verlof moet 14 dagen op voorhand aangevraagd worden"));
				return View.VERLOFAANVRAGEN;
			}
		} else {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage("", new FacesMessage(
					"De startdatum moet voor de einddatum liggen"));
			return View.VERLOFAANVRAGEN;
		}
	}

	/**
	 * functie die true weergeeft als het verlof op tijd is aangevraagd nl. 14
	 * dagen op voorhand
	 */
	public boolean isOpTijdAangevraagd() {
		return aanvraagDatum.toEpochDay()
				- startDatum.toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate().toEpochDay() < 14;
	}

	/**
	 * functie die true weergeeft als er geen overlappende verlofaanvraag is die
	 * al geannuleerd of afgekeurd is
	 */
	public boolean isNietOverlappend() {
//		start1.before(end2) && start2.before(end1);
//		bean.get(New Filter("aanvraagDatum", ))
//		Date date1 =
//		Date date2 = 
//		return startDatum.before(when) && .before(eindDatum) ? true : false;
		return true;
	}

	/**
	 * functie die true weergeeft als de werknemer nog genoeg verlofdagen heeft
	 */
	public boolean isGenoegVerlofdagen() {
		// TODO: genoeg verlofdagen halen uit datatabel
		return true;
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
