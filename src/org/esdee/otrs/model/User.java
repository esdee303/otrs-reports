package org.esdee.otrs.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.esdee.otrs.util.OtrsSQL;

public class User {
	String login;
	int id;

	public User(int id) {
		this.id = id;
		login = getLogin(id);
	}

	public User(String login) {
		this.login = login;
		id = getId(login);
	}
	
	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String getLogin(int id) {
		String login = "";
		String ss = "SELECT login FROM users WHERE id=" + id;
		try {
			ResultSet rs = OtrsSQL.dbExecuteQuery(ss);
			while (rs.next())
				login = rs.getString("login");
		} catch (ClassNotFoundException | SQLException | IOException e) {

			e.printStackTrace();
		}
		return login;
	}

	private int getId(String login) {
		int id = 0;
		String ss = "SELECT id FROM login WHERE login='" + login + "'";
		try {
			ResultSet rs = OtrsSQL.dbExecuteQuery(ss);
			while (rs.next())
				id = rs.getInt("id");
		} catch (ClassNotFoundException | SQLException | IOException e) {

			e.printStackTrace();
		}
		return id;
	}

	

}
