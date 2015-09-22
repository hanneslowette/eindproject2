package org.betavzw.view;

public interface View {

	/**
	 * De home view
	 */
	public static final String HOME = "home";

	/**
	 * View om een team op te vragen
	 */
	public static final String TEAM_OPVRAGEN = "team_opvragen";

	/**
	 * View om een team te kunnen toevoegen
	 */
	public static final String TEAM_TOEVOEGEN = "team_toevoegen";

	public static final String TEAM_BEHEER = "team_beheer";

	/**
	 * De view waar je werknemers kunt zoeken
	 */
	public static final String WERKNEMER_OPVRAGEN = "opvragenWerknemer";

	/**
	 * view om een werknemer toe te voegen
	 */
	public static final String WERKNEMER_TOEVOEGEN = "werknemerToevoegen";

	/**
	 * De view die de werknemer bewerkt
	 */
	public static final String WERKNEMER_WIJZIGEN = "wijzigen";

	/**
	 * View om een verlof aan te vragen
	 */
	public static final String VERLOFAANVRAGEN = "verlofaanvraag";

	/**
	 * De view die wordt weergegeven als de gebruiker voor het eerst verlof
	 * aanvraagt en deze wordt afgekeurd
	 */
	public static final String REJECTED = "verlofaanvraagafgekeurd";

	/**
	 * De view die wordt weergegeven als de administrator een verlof goedkeurt
	 */
	public static final String ACCEPTED = "verlofaanvraaggoedgekeurd";

	/**
	 * view om een verlof goed te keuren / af te keuren / cancellen
	 */
	public static final String VERLOFGOEDKEUREN = "verlofaanvraagkeuren";

	/**
	 * De view die wordt weergegeven als de gebruiker voor het eerst verlof
	 * aanvraagt
	 */
	public static final String PENDING = "verlofaanvraagpending";

	/**
	 * De view die wordt weergegeven als de gebruiker voor het eerst verlof
	 * aanvraagt
	 */
	public static final String VERSTUURD = "home";

	/**
	 * De view die wordt weergeven als de gebruiker een jaartal invoert (getal
	 * van 4 cijfers) en het aantal verlofdagen waar de werknemer dat jaar recht
	 * op heeft in. Als het jaartal niet in het verleden ligt, het aantal
	 * verlofdagen tussen 0 (incl.) en 50 (incl.) en voor deze werknemer en dit
	 * jaar nog geen aantal verlofdagen ingevoerd is, registreert het systeem de
	 * gegevens als de medewerker de gegevens bevestigt. Anders toont het
	 * systeem gepaste foutmeldingen
	 */

	public static final String JAARLIJKSVERLOF = "opvragenverlofperiodes";

}
