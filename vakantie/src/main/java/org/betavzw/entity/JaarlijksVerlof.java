package org.betavzw.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.betavzw.util.exceptions.FoutAantalVerlofdagenException;

@Entity
public class JaarlijksVerlof {

	/**
	 * De primary key
	 */
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;

	/**
	 * Het jaar waarin de verlofdagen genomen kunnen worden
	 */
	private int jaar;

	/**
	 * Het aantal dagen die men kan nemen
	 */
	private int aantalDagen;

	public JaarlijksVerlof() {
		// Default constructor voor Hibernate
	}

	public JaarlijksVerlof(int jaar, int aantalDagen) throws FoutAantalVerlofdagenException {
		setJaar(jaar);
		setAantalDagen(aantalDagen);
	}

	public int getJaar() {
		return jaar;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public int getAantalDagen() {
		return aantalDagen;
	}

	public void setAantalDagen(int aantalDagen) throws FoutAantalVerlofdagenException {
		
		if ((aantalDagen >= 20) && (aantalDagen <= 50)) {
			this.aantalDagen = aantalDagen;
		} else throw new FoutAantalVerlofdagenException();
		
	}
}
