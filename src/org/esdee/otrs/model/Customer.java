package org.esdee.otrs.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.esdee.otrs.util.OtrsSQL;

public class Customer {
	String id;
	String name;
	
	public Customer(String id) {
		this.id = id;
		name = getName();
	}
	
	
	
	
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		String name = "";
		String ss = "SELECT name FROM customer_company WHERE customer_id='" + id + "'";
		try {
			ResultSet rs = OtrsSQL.dbExecuteQuery(ss);
			while (rs.next())
				name = rs.getString("name");
		} catch (ClassNotFoundException | SQLException | IOException e) {

			e.printStackTrace();
		}
		return name;
	}
	
	public String getId() {
		String id = "";
		String ss = "SELECT customer_id FROM customer_company WHERE name='" + name + "'";
		try {
			ResultSet rs = OtrsSQL.dbExecuteQuery(ss);
			while (rs.next())
				id = rs.getString("id");
		} catch (ClassNotFoundException | SQLException | IOException e) {

			e.printStackTrace();
		}
		return id;
	}	
}
