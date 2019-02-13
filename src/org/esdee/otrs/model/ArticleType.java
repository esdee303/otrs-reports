package org.esdee.otrs.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.esdee.otrs.util.OtrsSQL;

public class ArticleType {
	int id;
	String name;
	
	
	public ArticleType(int id, int articleSenderTypeId) {
		this.id = id;
		name = getName(id, articleSenderTypeId);
	}
	
	public ArticleType(String name) {
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
	
	private String getName(int id, int articleSenderTypeId) {
		switch(id) {
		case 1: 	name = "eMail out";
					break;
		case 2:
				if (articleSenderTypeId == 1)
					name = "eMail internal";
				break;
		case 3:		
			switch (articleSenderTypeId) {
				case 2:		name = "eMail out";
				break;
			}
			break;
		case 5: 	
			switch (articleSenderTypeId) {
				case 3:		name = "New Tel. Ticket";
				case 1:		name = "Phone out";
				break;
			}
			break;
		case 8: 	name = "Time Registration";
					break;
		case 9:		name = "Note Internal";
					break;
		case 10:	name = "Note External";
					break;
	}
		return name;
	}
	
	private int getId(String name) {
		int id = 0;
		String ss = "SELECT id FROM article_type WHERE name='" + name + "'";
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
