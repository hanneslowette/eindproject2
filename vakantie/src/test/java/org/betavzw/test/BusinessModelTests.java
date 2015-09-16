/**
 * 
 */
package org.betavzw.test;

import static org.junit.Assert.assertTrue;

import org.betavzw.entities.Team;
import org.betavzw.entities.Werknemer;
import org.junit.Test;

/**
 * @author user107
 *
 */
public class BusinessModelTests {
	private Werknemer werknemer;
	private Team team;
//	private JaarlijksVerlof jaarlijksVerlof;
//	private CollectieveSluiting collectieveSluiting;
//	private Feestdag feestDag;
//	private CollectiefVerlof collectiefVerlof;
	
	public BusinessModelTests(){
		
	}
	@Test
	public void werknemerTest() {
		werknemer = new Werknemer();
		/** mag niet kunnen
		 * er MOET een team aan een werknemer hangen**/
		team = new Team();
		team.addWerknemer(werknemer);
		assertTrue(team.getTeamLeden().contains(werknemer));
	}
	
	@Test
	public void test(){
		werknemer.setPersoneelsNr(5);
		/** mag niet kunnen, databank moet dit genereren**/
	}
}
