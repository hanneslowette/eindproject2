package org.betavzw.test;

import static org.junit.Assert.assertEquals;

import org.betavzw.ejb.TeamEJB;
import org.betavzw.entities.Team;
import org.junit.Test;

public class EJBTests {
//
//	Team aanmaken(Team team) {
//		manager.persist(team);
//		return team;
	
	private TeamEJB teamEJB = new TeamEJB();
	
	@Test
	public void testAanmaken () {
		Team team = new Team();
		teamEJB.aanmaken(team);
		assertEquals("failure - teams not same", team, teamEJB.aanmaken(team));
	}
	
}
