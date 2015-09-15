package org.betavzw.ejb;

import java.util.List;

import org.betavzw.entities.Team;
import org.betavzw.entities.Werknemer;

public interface IWerknemer {
 public List<Team> getTeams();
 public List<Werknemer> getWerknemers();
 public Werknemer getWerknemer(String naam);
 public Team getTeam(String naam);
}
