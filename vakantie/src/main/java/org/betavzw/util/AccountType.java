package org.betavzw.util;

public enum AccountType {
	/**
	 * bezoekers
	 */
	BEZOEKER,
	/**
	 * mogen hun eigen verlofaanvragen zien
	 * en deze annuleren
	 */
	WERKNEMER,
	/**
	 * mogen de verlofaanvragen zien van elke werknemer in hun team
	 * en deze goed of afkeuren
	 */
	TEAMVERANTWOORDELIJKE,
	
	/**
	 * De HR kan alle teams bekijken maar niet bewerken.
	 */
	HR,
	
	/**
	 * De administrator kan alle teams en werknemers opvragen en bewerken
	 * en aanvragen goed- en afkeuren
	 */
	ADMINISTRATOR;

}
