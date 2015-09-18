package org.betavzw.view.io;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.betavzw.entity.Adres;
import org.betavzw.entity.Werknemer;
import org.betavzw.util.Filter;
import org.betavzw.util.QueryBuilder;
import org.betavzw.view.View;
import org.betavzw.view.bean.Bean;

/**
 * 
 * 
 * @author Brent
 *
 */
@Named("werknemerOpvragenIO")
@SessionScoped
public class WerknemerOpvragenIO implements Serializable {

	/**
	 * Versie id van het geserialiseerd object
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * De bean die instaat voor de werknemers
	 */
	@Inject
	private Bean<Werknemer> werknemer_bean;

	/**
	 * De door de gebruiker ingevulde achternaam
	 */
	private String naam;

	/**
	 * De door de gebruiker ingevulde voornaam
	 */
	private String voornaam;

	/**
	 * De door de gebruiker ingevulde personeelsnummer
	 */
	private Integer personeelsNummer;

	/**
	 * entity werknemer om te wijzigen
	 */
	private Werknemer werknemer;

	/**
	 * De opgevraagde lijst met werknemers
	 */
	private List<Werknemer> lijst = new ArrayList<Werknemer>();

	/**
	 * De code die wordt uitgevoerd wanneer de gebruiker op "zoek" klikt
	 */
	public String zoek() {
		List<Filter> filterlist = new ArrayList<Filter>();
		if (!naam.equals("")) {
			filterlist.add(new Filter("naam", "%" + this.naam + "%"));
		}
		if (!voornaam.equals("")) {
			filterlist.add(new Filter("voornaam", "%" + this.voornaam + "%"));
		}
		if (personeelsNummer != null) {
			filterlist.add(new Filter("personeelsNr", this.personeelsNummer));
		}
		lijst = werknemer_bean.get(new QueryBuilder().addFilter(filterlist)
				.sort("personeelsNr"));
		if (lijst.size() == 0) {
			FacesContext ctx = FacesContext.getCurrentInstance();
			ctx.addMessage(null, new FacesMessage("Geen zoekresultaten",
					"Geen zoekresultaten"));
		}
		return null;
	}

	/**
	 * Zet de geselecteerde werknemer als werknemer die bewerkt wordt en gaat
	 * dan naar de view die hem kan bewerken
	 * 
	 * @param id
	 * @return werknemer wijzigen pagina
	 */
	public String wijzig(int id) {
		setWerknemer(werknemer_bean.getSingle(new Filter("id", id)));
		return View.WERKNEMER_WIJZIGEN;
	}

	/**
	 * bij het wijzigen van een werknemer kan je op save drukken en wordt dit
	 * uitgevoerd
	 * 
	 * @return werknemer_opvragen xhtml string
	 */
	public String save() {
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
			ctx.addMessage(null, new FacesMessage("Geen naam",
					"Geen naam"));
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
			ctx.addMessage(null, new FacesMessage("Geen email",
					"Geen email"));
		}
		
		if (werknemer.getGeboortedatum() == null) {
			missingComponents = true;
			ctx.addMessage(null, new FacesMessage("Geen geboortedatum",
					"Geen geboortedatum"));
		}
		
		if (werknemer.getTeam() == null) {
			missingComponents = true;
			ctx.addMessage(null, new FacesMessage("Geen team",
					"Geen team"));
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
			werknemer_bean.update(werknemer);
			lijst = new ArrayList<Werknemer>();
			ctx.addMessage(null, new FacesMessage("werknemer aangepast",
					"update geslaagd"));
			return View.WERKNEMER_OPVRAGEN;
		}
	}

	public List<Werknemer> getLijst() {
		return lijst;
	}

	public void setLijst(List<Werknemer> lijst) {
		this.lijst = lijst;
	}

	public Werknemer getWerknemer() {
		return werknemer;
	}

	public void setWerknemer(Werknemer werknemer) {
		this.werknemer = werknemer;
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

	public Integer getPersoneelsNummer() {
		return personeelsNummer;
	}

	public void setPersoneelsNummer(Integer personeelsNummer) {
		this.personeelsNummer = personeelsNummer;
	}

}