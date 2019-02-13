package org.esdee.otrs.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.esdee.otrs.util.OtrsSQL;

public class CustomerUser {
	String login;
	String name;
	
	
	public CustomerUser(String login) {
		this.login = login;
		name = getName(login);
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}
	
	public String getName() {
		return name;
	}
	
	private String getName(String login) {
		String name = "";
		String ss = "SELECT first_name,last_name FROM customer_user WHERE login='" + login + "'";
		try {
			ResultSet rs = OtrsSQL.dbExecuteQuery(ss);
			if (rs.next()) {
				name = rs.getString("first_name") + " " + rs.getString("last_name");
			} else {
				name = login;
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	
}
