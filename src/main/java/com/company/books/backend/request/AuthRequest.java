package com.company.books.backend.request;

import java.io.Serializable;

public class AuthRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3321787219997402977L;
	
	private String user;
	private String password;
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
