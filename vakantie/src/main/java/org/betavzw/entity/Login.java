package org.betavzw.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Login {

	private String username;
	private String password;

	@Id
	private int werknemerId;

}
