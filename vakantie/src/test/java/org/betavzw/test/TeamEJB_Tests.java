package org.betavzw.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.betavzw.ejb.TeamEJB;
import org.betavzw.entities.Team;
import org.betavzw.entities.Werknemer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
		assertEquals("failure - werknemers don't match",
				team.getTeamverantwoordelijke(), werknemer);

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
		assertEquals("failure - werknemers don't match",
				team.getTeamverantwoordelijke(), werknemer);
		assertEquals("failure - teamLeden don't match", team.getTeamLeden(),
				collection);

	}

	@Test
	public void testAanmakenMuitipleVariables() {

		String naam = "Team Green";
		String code = "GREEN01";
		Werknemer werknemer = new Werknemer();
		Werknemer werknemer2 = new Werknemer();
		Werknemer werknemer3 = new Werknemer();
		Collection<Werknemer> inCollection = new ArrayList<Werknemer>();
		inCollection.add(werknemer);
		inCollection.add(werknemer2);
		inCollection.add(werknemer3);

		Team team = teamEJB.aanmaken(naam, code, werknemer, werknemer,
				werknemer2, werknemer3);

		assertEquals("failure - names don't match", team.getNaam(), naam);
		assertEquals("failure - codes don't match", team.getCode(), code);
		assertEquals("failure - teamverantwoordelijke don't match",
				team.getTeamverantwoordelijke(), werknemer);

		Collection<Werknemer> outCollection = team.getTeamLeden();
		assertTrue(
				"failure - werknemers don't match",
				(inCollection.size() == outCollection.size())
						&& inCollection.containsAll(outCollection)
						&& inCollection.containsAll(outCollection));

	}

	@Test
	public void testVoegTeamToe() {
		Team team = new Team();
		teamEJB.voegTeamToe(team);
//		List<Team> collection = teamEJB.getTeams(); // broken?
//		assertTrue(collection.contains(team));
		assertTrue("MAJOR FAILURE", false);
	}
	
	@Test
	public void testVerwijderTeam() {
		Team team = new Team();
//		teamEJB.voegTeamToe(team);
		teamEJB.verwijderTeam(team);
	} 
	
	@Test
	public void testWijzigTeam() {
		Team team = new Team();
		teamEJB.voegTeamToe(team);
		team.setNaam("Jan Van Gent");
		teamEJB.wijzigTeam(team);
	}
	
}
