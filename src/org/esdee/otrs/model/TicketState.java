package org.esdee.otrs.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.esdee.otrs.util.OtrsSQL;

public class TicketState {
	int id;
	String name;
	
	public TicketState(int id) {
		this.id = id;
		name = getName(id);
	}
	
	public TicketState(String name) {
		this.name = name;
		id = getId(name);
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	private String getName(int id) {
		String name = "";
		String ss = "SELECT name FROM ticket_state WHERE id=" + id;
		try {
			ResultSet rs = OtrsSQL.dbExecuteQuery(ss);
			while (rs.next())
				name = rs.getString("name").substring(0, 1).toUpperCase() + rs.getString("name").substring(1);
		} catch (ClassNotFoundException | SQLException | IOException e) {

			e.printStackTrace();
		}
		return name;
	}
	
	private int getId(String name) {
		int id = 0;
		String ss = "SELECT id FROM ticket_state WHERE name='" + name + "'";
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
