package org.betavzw.view.io;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.betavzw.entity.JaarlijksVerlof;
import org.betavzw.entity.VerlofAanvraag;
import org.betavzw.util.Filter;
import org.betavzw.util.Mail;
import org.betavzw.util.Toestand;
import org.betavzw.util.exceptions.GeboortedatumInDeToekomstException;
import org.betavzw.view.View;
import org.betavzw.view.bean.Bean;
import org.betavzw.view.bean.LoginBean;
import org.betavzw.view.bean.WerknemerBean;

@Named("verlofAanvraag")
@SessionScoped
public class VerlofAanvraagIO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Bean<VerlofAanvraag> verlofAanvraag_bean;
	@Inject
	private LoginBean loginbean;
	@Inject
	private WerknemerBean werknemerbean;

	@Temporal(TemporalType.DATE)
	@NotNull(message = "Veld startdatum moet ingevuld zijn")
	private Date startDatum;
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Veld einddatum moet ingevuld zijn")
	private Date eindDatum;
	private LocalDate aanvraagDatum = LocalDate.now();
	private FacesContext facesContext = FacesContext.getCurrentInstance();

	public VerlofAanvraagIO() {
		super();
	}

	/**
	 * verstuurfunctie voor commandButton van verlofaanvragen.xhtml waar een
	 * nieuwe verlofaanvraag wordt aangemaakt in de databank
	 * 
	 * @throws GeboortedatumInDeToekomstException
	 */
	public String verstuur() {
		if (eindDatum.before(startDatum)) {
			facesContext.addMessage("", new FacesMessage(
					"De startdatum moet voor de einddatum liggen"));
			return View.VERLOFAANVRAAG;
		}
		if (!isOpTijdAangevraagd()) {
			facesContext.addMessage("", new FacesMessage(
					"Het verlof moet 14 dagen op voorhand aangevraagd worden"));
			return View.VERLOFAANVRAAG;
		}
		if (isOverlappend()) {
			facesContext
					.addMessage(
							"",
							new FacesMessage(
									"De verlofaanvraag mag niet overlappen met een andere geannuleerde of afgekeurde verlofaanvraag"));
			return View.VERLOFAANVRAAG;
		}
		if (!isGenoegVerlofdagen()) {
			facesContext
					.addMessage(
							"",
							new FacesMessage(
									"U heeft niet genoeg verlofdagen om deze verlofaanvraag te kunnen maken"));
			return View.VERLOFAANVRAAG;
		}
		if (teveeldagenpending()) {
			facesContext.addMessage("", new FacesMessage(
					"U heeft te veel dagen pending"));
			return View.VERLOFAANVRAAG;
		}
		VerlofAanvraag verlofAanvraag = new VerlofAanvraag();
		verlofAanvraag.setStartDatum(startDatum.toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDate());
		verlofAanvraag.setEindDatum(eindDatum.toInstant()
				.atZone(ZoneId.systemDefault()).toLocalDate());
		verlofAanvraag.setAanvraagDatum(aanvraagDatum);
		verlofAanvraag.setToestand(Toestand.PENDING);
		verlofAanvraag.setWerknemer(loginbean.getWerknemer());
		System.out.println("pre-offer");
		verlofAanvraag_bean.offer(verlofAanvraag);
		System.out.println("post-offer");
		try {
			Mail.send("teamredconfirmation", "needabijtnie", loginbean
					.getWerknemer().getEmail(), "Uw verlofaanvraag",
					"Uw verlofaanvraag is aangekomen");
		} catch (AddressException e) {
			System.out.println("AddressException has been caught:");
			e.printStackTrace();
		} catch (MessagingException e) {
			System.out.println("MessagingException has been caught:");
			facesContext
					.addMessage(
							"",
							new FacesMessage(
									"Mail versturen mislukt maar verlofaanvraag is aangekomen"));
		}
		werknemerbean.update(loginbean.getWerknemer());
		return View.VERSTUURD;
	}

	/**
	 * functie die true weergeeft als het verlof op tijd is aangevraagd nl. 14
	 * dagen op voorhand
	 */
	public boolean isOpTijdAangevraagd() {
		LocalDate start = startDatum.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		Period tussenperiode = Period.between(aanvraagDatum, start);
		if (tussenperiode.getYears() > 0 || tussenperiode.getMonths() > 0
				|| tussenperiode.getDays() > 14) {
			return true;
		}
		return false;
	}

	/**
	 * functie die true weergeeft als er een overlappende verlofaanvraag is die
	 * al geannuleerd of afgekeurd is
	 */
	public boolean isOverlappend() {
		java.sql.Date sqlInputStartDatum = new java.sql.Date(
				startDatum.getTime());
		java.sql.Date sqlInputEindDatum = new java.sql.Date(eindDatum.getTime());
		org.betavzw.util.Period periode = new org.betavzw.util.Period(
				sqlInputStartDatum, sqlInputEindDatum);
		List<VerlofAanvraag> verlofAanvragen = new ArrayList<VerlofAanvraag>();
		verlofAanvragen = verlofAanvraag_bean.get(new Filter(
				"werknemer.personeelsNr", loginbean.getWerknemer()
						.getPersoneelsNr()));
		List<org.betavzw.util.Period> periodes = new ArrayList<org.betavzw.util.Period>();
		for (VerlofAanvraag verlofAanvraag : verlofAanvragen) {
			java.sql.Date sqlStartDatum = java.sql.Date.valueOf(verlofAanvraag
					.getStartDatum());
			java.sql.Date sqlEindDatum = java.sql.Date.valueOf(verlofAanvraag
					.getEindDatum());
			periodes.add(new org.betavzw.util.Period(sqlStartDatum,
					sqlEindDatum));
		}
		boolean overlaps = false;
		for (org.betavzw.util.Period period : periodes) {
			if (periode.overlaps(period)) {
				overlaps = true;
			}
		}
		return overlaps;
	}

	/**
	 * functie die true weergeeft als de werknemer nog genoeg verlofdagen heeft
	 * Deze werkt mogelijk nog niet
	 */
	public boolean isGenoegVerlofdagen() {
		LocalDate start = startDatum.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		LocalDate eind = eindDatum.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		Period periode = Period.between(start, eind);
		long aanvraagdagen = periode.get(ChronoUnit.DAYS);
		int verlofdagen = 0;
		for (JaarlijksVerlof jaar : loginbean.getWerknemer()
				.getJaarlijkseVerloven()) {
			if (jaar.getJaar() == start.getYear()) {
				verlofdagen += jaar.getAantalDagen();
			}
		}
		System.out.println("aanvraagdagen: " + aanvraagdagen);
		System.out.println("verlofdagen: " + verlofdagen);
		if (aanvraagdagen > verlofdagen) {
			return false;
		}
		return true;
	}

	/**
	 * functie die true terug geeft wanneer er teveel dagen pending zijn om deze
	 * verlofaanvraag in te dienen (als verlofdagen pending + nieuwe aanvraag
	 * een groter aantal is dan het toegelaten aantal verlofdagen dat jaar)
	 * 
	 * @return
	 */
	public boolean teveeldagenpending() {
		LocalDate start = startDatum.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		LocalDate eind = eindDatum.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		int verlofdagen = 0;
		for (JaarlijksVerlof jaar : loginbean.getWerknemer()
				.getJaarlijkseVerloven()) {
			if (jaar.getJaar() == start.getYear()) {
				verlofdagen += jaar.getAantalDagen();
			}
		}
		Period periode = Period.between(start, eind);
		long aanvraagdagen = periode.get(ChronoUnit.DAYS);
		int pendingdagen = 0;
		for (VerlofAanvraag aanvraag : loginbean.getWerknemer()
				.getVerlofAanvragen()) {
			if (aanvraag.getToestand().equals(Toestand.PENDING)) {
				System.out.println("PENDING");
				periode = Period.between(aanvraag.getStartDatum(),
						aanvraag.getEindDatum());
				System.out.println(periode.get(ChronoUnit.DAYS));
				pendingdagen += periode.get(ChronoUnit.DAYS);
			}
		}
		System.out.println("pendingdagen: " + pendingdagen);
		if (aanvraagdagen + pendingdagen > verlofdagen) {
			return true;
		}
		return false;
	}

	public LocalDate getAanvraagDatum() {
		return aanvraagDatum;
	}

	public void setAanvraagDatum(LocalDate aanvraagDatum) {
		this.aanvraagDatum = aanvraagDatum;
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
