package org.betavzw.view;

public interface View {

	/**
	 * Author Brent Courtois, senior java developer specialist architect, &
	 * otter Hannes
	 **/

	/**
	 * De home view
	 */
	public static final String HOME = "home";
	/**
	 * View om een team op te vragen
	 */
	public static final String TEAM_OPVRAGEN = "opvragenTeam";
	/**
	 * View om een team te kunnen toevoegen
	 */
	public static final String TEAM_TOEVOEGEN = "teamToevoegen";
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
	 * View met uw verlofaanvragen
	 */
	public static final String VERLOFAANVRAGEN = "verlofaanvragen";
	/**
	 * De view die wordt weergegeven als de gebruiker voor het eerst verlof
	 * aanvraagt
	 */
	public static final String PENDING = "verlofpending";
	/**
	 * De view die wordt weergegeven als de administrator een verlof goedkeurt
	 */
	public static final String ACCEPTED = "verlofgoedgekeurd";
	/**
	 * De view die wordt weergegeven als de gebruiker voor het eerst verlof
	 * aanvraagt en deze wordt afgekeurd
	 */
	public static final String REJECTED = "verlofafgekeurd";
	/**
	 * De view die wordt weergegeven als de gebruiker voor het eerst verlof
	 * aanvraagt
	 */
	public static final String VERSTUURD = "verlofaanvraagverstuurd";
	/**
	 * Hier kan je via personeelsnummer de jaarlijkse verloven van die werknemer
	 * bekijken
	 */
	public static final String JAARLIJKSVERLOF = "beheerjaarlijkseverloven";
	/**
	 * view om een verlof goed te keuren/ af te keuren
	 */
	public static final String VERLOFGOEDKEUREN = "verlofgoedkeuren";
}
