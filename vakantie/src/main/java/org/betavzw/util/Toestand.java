package org.betavzw.util;

/**
 * Duid de toestand aan van de verlof aanvraag
 * 
 * @author Hannes Lowette
 *
 */
public enum Toestand {
	
	/**
	 * De toestand wanneer de aanvraag is gebeurd, maar nog niet is beoordeeld door
	 * de verantwoordelijke
	 */
	PENDING,
	
	/**
	 * De toestand wanneer de verantwoordelijke de aanvraag heeft goedgekeurd
	 */
	ACCEPTED,
	
	/**
	 * De toestand wanneer de verantwoordelijke de aanvraagd heeft afgekeurd
	 */
	REJECTED,
	
	/**
	 * De toestand wanneer de aanvraag is gestopt door de werknemer
	 */
	CANCELED;
}