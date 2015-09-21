package org.betavzw.view.io;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.Adres;
import org.betavzw.entity.Team;
import org.betavzw.entity.Werknemer;
import org.betavzw.util.Filter;
import org.betavzw.util.QueryBuilder;
import org.betavzw.util.exceptions.GeboortedatumInDeToekomstException;
import org.betavzw.view.View;
import org.betavzw.view.bean.Bean;

@Named
@SessionScoped
public class WerknemerToevoegenIO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Bean<Werknemer> werknemer_bean;
	
	@Inject
	private Bean<Team> team_bean;
	
	private Werknemer werknemer = new Werknemer();
	private Date datum;
	private int teamId;
	
	@PostConstruct
	public void makeAdres(){
		werknemer.setAdres(new Adres());
	}
	
	public List<Team> getTeams() {
		return team_bean.get();
	}
	
	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public Werknemer getWerknemer() {
		return werknemer;
	}

	public void setWerknemer(Werknemer werknemer) {
		this.werknemer = werknemer;
	}
	
	public Date getDatum() {
		return werknemer.getGeboortedatum() == null ? null : Date
				.from(werknemer.getGeboortedatum().atStartOfDay()
						.atZone(ZoneId.systemDefault()).toInstant().plusSeconds(86001));
	}

	public void setDatum(Date datum) {
		this.datum = datum;
		try {
//			LocalDate localdate = datum.toInstant()
//					.atZone(ZoneId.systemDefault()).toLocalDate();
			Instant instant = Instant.ofEpochMilli(datum.getTime());
			LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
			werknemer.setGeboortedatum(res);
			
		} catch (GeboortedatumInDeToekomstException e) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							null,
							new FacesMessage(
									"werknemer niet aangepast: geboortedatum mag niet in de toekomst liggen",
									"update geslaagd"));
		}
	}
	
	public String voegWerknemerToe() {
		boolean missingComponents = false;
		FacesContext ctx = FacesContext.getCurrentInstance();

		/**
		 * adres checken op leegheid
		 */
		Adres adres = werknemer.getAdres();
		if (adres == null) {
			ctx.addMessage(null, new FacesMessage("Geen adres", "Geen adres"));
			missingComponents = true;
		} else {
			if (adres.getStraat() == null
					|| adres.getStraat().equalsIgnoreCase("")) {
				missingComponents = true;
				ctx.addMessage(null, new FacesMessage("Geen straat",
						"Geen straat"));
			}
			if (adres.getHuisnummer() == null
					|| adres.getHuisnummer().equalsIgnoreCase("")) {
				missingComponents = true;
				ctx.addMessage(null, new FacesMessage("Geen huisnummer",
						"Geen huisnummer"));
			}
			if (adres.getGemeente() == null
					|| adres.getGemeente().equalsIgnoreCase("")) {
				missingComponents = true;
				ctx.addMessage(null, new FacesMessage("Geen gemeente",
						"Geen gemeente"));
			}
			if (adres.getPostcode() == null
					|| adres.getPostcode().equalsIgnoreCase("")) {
				missingComponents = true;
				ctx.addMessage(null, new FacesMessage("Geen postcode",
						"Geen postcode"));
			}
		}
		/**
		 * andere componenten checken op leegheid
		 */
		if (werknemer.getNaam() == null
				|| werknemer.getNaam().equalsIgnoreCase("")) {
			missingComponents = true;
			ctx.addMessage(null, new FacesMessage("Geen naam", "Geen naam"));
		}

		if (werknemer.getVoornaam() == null
				|| werknemer.getVoornaam().equalsIgnoreCase("")) {
			missingComponents = true;
			ctx.addMessage(null, new FacesMessage("Geen voornaam",
					"Geen voornaam"));
		}

		if (werknemer.getEmail() == null
				|| werknemer.getEmail().equalsIgnoreCase("")) {
			missingComponents = true;
			ctx.addMessage(null, new FacesMessage("Geen email", "Geen email"));
		}

		if (werknemer.getGeboortedatum() == null) {
			missingComponents = true;
			ctx.addMessage(null, new FacesMessage("Geen geboortedatum",
					"Geen geboortedatum"));
		}

		if (werknemer.getTeam() == null) {
			missingComponents = true;
			ctx.addMessage(null, new FacesMessage("Geen team", "Geen team"));
		}
		/**
		 * als er ook maar één ding mis is:
		 */
		if (missingComponents) {
			return View.WERKNEMER_WIJZIGEN;
		} else {
			/**
			 * als alles is ingevuld:
			 */
			werknemer.setTeam(team_bean.getSingle(new QueryBuilder()
					.addFilter(new Filter("id", teamId))));
			werknemer_bean.offer(werknemer);
			ctx.addMessage(null, new FacesMessage("werknemer aangepast",
					"update geslaagd"));
			return View.WERKNEMER_OPVRAGEN;
		}
	}
	
}