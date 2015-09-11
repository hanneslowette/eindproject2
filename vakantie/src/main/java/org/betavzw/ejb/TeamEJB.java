package org.betavzw.ejb;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.betavzw.entities.Team;
import org.betavzw.entities.Werknemer;

/**
 * 1.2. Beheren van teams
 * Als HR-medewerker
 * wil ik gegevens van teams kunnen beheren in het systeem
 * zodat de teamgegevens in het systeem up-to-date gehouden kunnen worden en
 * teamverantwoordelijken verlofaanvragen kunnen verwerken
 * 
 * @author Hannes Lowette
 */
@LocalBean
@Stateless public class TeamEJB {

	/**
	 * TODO: naam persistence context
	 */
	@PersistenceContext private EntityManager manager;

	/**
	 * Registreert een team in de entity manager en geeft dit terug (voor chaining)
	 * 
	 * @param team
	 * @return
	 */
	@Transactional public Team aanmaken(Team team) {
		manager.persist(team);
		return team;
	}

	/**
	 * Maakt een nieuw team aan met de gegeven waarden en geeft het aangemaakte team terug
	 * 
	 * @param naam
	 * @param code
	 * @param teamverantwoordelijke
	 * @return
	 */
	public Team aanmaken(String naam, String code, Werknemer teamverantwoordelijke) {
		Team team = new Team();
		team.setNaam(naam);
		team.setCode(code);
		team.setTeamverantwoordelijke(teamverantwoordelijke);
		team.setTeamLeden(new HashSet<>());
		return this.aanmaken(team);
	}

	/**
	 * Maakt een nieuw team aan met de gegeven waarden en geeft het aangemaakte team terug
	 * 
	 * @param naam
	 * @param code
	 * @param verantwoordelijke
	 * @param werknemers
	 * @return
	 */
	public Team aanmaken(String naam, String code, Werknemer verantwoordelijke, Collection<Werknemer> werknemers) {
		Team team = this.aanmaken(new Team());
		team.setTeamLeden(new HashSet<>(werknemers));
		return team;
	}

	/**
	 * Maakt een nieuw team aan met de gegeven waarden en geeft het aangemaakte team terug
	 * 
	 * @param naam
	 * @param code
	 * @param teamverantwoordelijke
	 * @return
	 */
	public Team aanmaken(String naam, String code, Werknemer teamverantwoordelijke, Werknemer... werknemers) {
		return this.aanmaken(naam, code, teamverantwoordelijke, Arrays.asList(werknemers));
	}

	/**
	 * Persisteert het team
	 * 
	 * @param team
	 */
	@Transactional public void commit(Team team) {
		manager.persist(team);
	}

	/**
	 * Krijg een team uit de database aan de hand van de id
	 * 
	 * @param id
	 * @return
	 */
	@Transactional public Team get(int id) {
		return manager.find(Team.class, id);
	}

}