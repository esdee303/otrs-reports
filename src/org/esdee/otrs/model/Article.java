package org.esdee.otrs.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import org.esdee.otrs.util.*;

public class Article {
	private static final DateTimeFormatter yyyyMMdd = DateTimeFormat.forPattern("yyyy-MM-dd");
	int id;
	int tn;
	int ticketId;
	String articleType;
	String articleSenderTypeId;
	String title;
	String createBy;
	String createTime;
	String timeRegistration;
	
	public Article() {}
	
	public static List<Article> articlesForUser(int userId, DateTime weekStart) {
		String weekdate = yyyyMMdd.print(weekStart);
		List<Article> articlesForUser = new ArrayList<Article>();
		String ss = "SELECT id,ticket_id,article_type_id,article_sender_type_id,create_by,create_time,a_subject FROM article "
				+ "WHERE create_by = " + userId + " AND create_time > '" + weekdate + "'";
		try {
			ResultSet rs = OtrsSQL.dbExecuteQuery(ss);
			while (rs.next()) {
				Article article = new Article();
				article.setArticleId(rs.getInt("id"));
				article.setTicketId(rs.getInt("ticket_id"));
				article.setTn(Ticket.getTn(article.getTicketId()));
				article.setTitle(Ticket.getTitle(article.getTn()));
				article.setArticleType(new ArticleType(rs.getInt("article_type_id"), rs.getInt("article_sender_type_id")).getName());
				article.setCreateBy(new User(rs.getInt("create_by")).getLogin());
				article.setCreateTime(rs.getString("create_time").substring(0, 19));
				if(article.getArticleType().equals("Time Registration")) {
					article.setTimeRegistration(TimeRegistration.getTimeRegistration(article.getId()));
				} else {
					article.setTimeRegistration("---");
				}
				articlesForUser.add(article);
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return articlesForUser;
	}
	
	public void setTimeRegistration(String timeRegistration) {
		this.timeRegistration = timeRegistration;
	}
	
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setArticleId(int id) {
		this.id = id;
	}
	public void setTn(int tn) {
		this.tn = tn;
	}
	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}
	public void setArticleSenderTypeId(String articleSenderTypeId) {
		this.articleSenderTypeId = articleSenderTypeId;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getTimeRegistration() {
		return timeRegistration;
	}
	
	public String getTitle() {
		return title;
	}

	public int getTicketId() {
		return ticketId;
	}
	public int getId() {
		return id;
	}
	public int getTn() {
		return tn;
	}
	public String getArticleType() {
		return articleType;
	}
	public String getArticleSenderTypeId() {
		return articleSenderTypeId;
	}
	public String getCreateBy() {
		return createBy;
	}
	public String getCreateTime() {
		return createTime;
	}
}
