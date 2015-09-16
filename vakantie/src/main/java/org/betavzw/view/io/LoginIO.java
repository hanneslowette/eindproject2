package org.betavzw.view.io;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.betavzw.entity.Login;
import org.betavzw.util.Filter;
import org.betavzw.view.bean.AbstractBean;

@Named("login")
@SessionScoped
public class LoginIO extends AbstractBean<Login> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	public String aanmelden() {

		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public List<Login> get(Filter... filters) {
		// TODO Auto-generated method stub
		return null;
	}
}