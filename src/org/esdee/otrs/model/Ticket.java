package org.esdee.otrs.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.esdee.otrs.util.OtrsSQL;

public class Ticket {
	int tn;
	int id;
	String queue;
	String createTime;
	String changeTime;
	String ticketState;
	String responsible;
	String owner;
	String customer;
	String customerUser;
	String title;
	
	public Ticket() {}
	
	public static Ticket getTicketsFromTn(int tn) {
		Ticket ticket = new Ticket();
		String ss = "SELECT id,tn,queue_id,create_time,change_time,ticket_state_id,responsible_user_id,user_id,customer_id,customer_user_id,title "
				+ "FROM ticket WHERE tn=" + tn;
		try {
			ResultSet rs = OtrsSQL.dbExecuteQuery(ss);
			while (rs.next()) {
				ticket.setId(rs.getInt("id"));
				ticket.setTn(rs.getInt("tn"));
				ticket.setQueue(new Queue(rs.getInt("queue_id")).getName());
				ticket.setCreateTime(rs.getString("create_time").substring(0, rs.getString("create_time").length() - 2));
				ticket.setChangeTime(rs.getString("change_time").substring(0, rs.getString("change_time").length() - 2));
				ticket.setTicketState(new TicketState(rs.getInt("ticket_state_id")).getName());
				ticket.setResponsible(new User(rs.getInt("responsible_user_id")).getLogin());
				ticket.setOwner(new User(rs.getInt("responsible_user_id")).getLogin());
				ticket.setCustomer(new Customer(rs.getString("customer_id")).getName());
				ticket.setCustomerUser(new CustomerUser(rs.getString("customer_user_id")).getName());
				ticket.setTitle(rs.getString("title"));
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return ticket;
	}
	
	public static List<Ticket> getTicketsWithCreateTime(String createTime, boolean beforeOrAfter) {
		List<Ticket> ticketList = new ArrayList<Ticket>();
		return ticketList;
	}
	
	public static int getId(int tn) {
		int id = 0;
		String ss = "SELECT id FROM ticket WHERE tn=" + tn;
		try {
			ResultSet rs = OtrsSQL.dbExecuteQuery(ss);
			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	public static int getTn(int id) {
		int tn = 0;
		String ss = "SELECT tn FROM ticket WHERE id=" + id;
		try {
			ResultSet rs = OtrsSQL.dbExecuteQuery(ss);
			while (rs.next()) {
				tn = rs.getInt("tn");
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return tn;
	}
	
	public static String getTitle(int tn) {
		String title = "";
		String ss = "SELECT title FROM ticket WHERE tn=" + tn;
		try {
			ResultSet rs = OtrsSQL.dbExecuteQuery(ss);
			while (rs.next()) {
				title = rs.getString("title");
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return title;
	}
	
	public static String getCreateTime(int tn) {
		String createTime = "";
		String ss = "SELECT create_time FROM ticket WHERE tn=" + tn;
		try {
			ResultSet rs = OtrsSQL.dbExecuteQuery(ss);
			while(rs.next()) {
				createTime = rs.getString("create_time").substring(0, 19);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return createTime;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setTn(int tn) {
		this.tn = tn;
	}

	public int getTn() {
		return tn;
	}

	public void setQueue(String queue) {
		this.queue = queue;
	}

	public String getQueue() {
		return queue;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setChangeTime(String changeTime) {
		this.changeTime = changeTime;
	}

	public String getChangeTime() {
		return changeTime;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public String getTicketState() {
		return ticketState;
	}

	public void setTicketState(String ticketState) {
		this.ticketState = ticketState;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwner() {
		return owner;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomerUser(String customerUser) {
		this.customerUser = customerUser;
	}

	public String getCustomerUser() {
		return customerUser;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public String toString() {
		return id + "\t" + tn + "\t" + queue + "\t" + createTime + "\t" + changeTime + "\t" + ticketState + "\t"
				+ responsible + "\t" + owner + "\t" + customer + "\t" + customerUser + "\t" + title;
	}
}
