package org.betavzw.view;

public interface View {
	
	/**
	 * De home view
	 */
	public static final String HOME = "home";

	/**
	 * De view die de werknemer bewerkt
	 */
	public static final String WIJZIG_WERKNEMER = "wijzigWerknemer";
	
	/**
	 * De view die wordt weergegeven als de gebruiker voor het eerst verlof aanvraagt
	 */
	public static final String PENDING = "verlofpending";
	
	/**
	 * De view die wordt weergegeven als de gebruiker voor het eerst verlof aanvraagt
	 */
	public static final String ACCEPTED = "verlofgoedgekeurd";
	
	/**
	 * De view die wordt weergegeven als de gebruiker voor het eerst verlof aanvraagt
	 */
	public static final String REJECTED = "verlofafgekeurd";

}
