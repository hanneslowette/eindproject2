package org.betavzw.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.betavzw.ejb.TeamEJB;
import org.betavzw.entities.Team;
import org.betavzw.entities.Werknemer;
import org.junit.Test;

public class TeamEJB_Tests {
	
	private TeamEJB teamEJB = new TeamEJB();
	
	@Test
	public void testAanmaken1Variable() {
		Team team = new Team();
		teamEJB.aanmaken(team);
		assertEquals("failure - teams not same", team, teamEJB.aanmaken(team));
	}
	
	@Test
	public void testAanmaken3Variables() {
		
		String naam = "Team Green";
		String code = "GREEN01";
		Werknemer werknemer = new Werknemer();
		
		Team team = teamEJB.aanmaken(naam, code, werknemer);
		
		assertEquals("failure - names don't match", team.getNaam(), naam);
		assertEquals("failure - codes don't match", team.getCode(), code);
		assertEquals("failure - werknemers don't match", team.getTeamverantwoordelijke(), werknemer);
		
	}
	
	@Test
	public void testAanmaken4Variables() {
		
		String naam = "Team Green";
		String code = "GREEN01";
		Werknemer werknemer = new Werknemer();
		Werknemer werknemer2 = new Werknemer();
		Collection<Werknemer> collection = new ArrayList<Werknemer>();
		collection.add(werknemer);
		collection.add(werknemer2);
		
		Team team = teamEJB.aanmaken(naam, code, werknemer, collection);
		
		assertEquals("failure - names don't match", team.getNaam(), naam);
		assertEquals("failure - codes don't match", team.getCode(), code);
		assertEquals("failure - werknemers don't match", team.getTeamverantwoordelijke(), werknemer);
		assertEquals("failure - teamLeden don't match", team.getTeamLeden(), collection);
		
	}
	
}
