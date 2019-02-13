package org.esdee.otrs.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.esdee.otrs.util.OtrsSQL;

public class Queue {
	int id;
	String name;
	
	public Queue(int id) {
		this.id = id;
		name = getName(id);
	}
	
	public Queue(String name) {
		this.name = name;
		id = getId(name);
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	private String getName(int id) {
		String name = "";
		String ss = "SELECT name FROM queue WHERE id=" + id;
		try {
			ResultSet rs = OtrsSQL.dbExecuteQuery(ss);
			while (rs.next())
				name = rs.getString("name").substring(rs.getString("name").lastIndexOf(":")+1, rs.getString("name").length());
		} catch (ClassNotFoundException | SQLException | IOException e) {

			e.printStackTrace();
		}
		return name;
	}
	
	private int getId(String name) {
		int id = 0;
		String ss = "SELECT id FROM queue WHERE name='" + name + "'";
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
