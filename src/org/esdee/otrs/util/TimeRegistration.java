package org.esdee.otrs.util;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeRegistration {
	private static final DateTimeFormatter yyyyMMddHHmmss = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	
	public TimeRegistration() {}
	
	public static String getTimeRegistration (int articleId) {
		String time = "";
		try {
			DateTime field_72 = new DateTime();
			DateTime field_73 = new DateTime();
				String ss2 = "SELECT value_date FROM dynamic_field_value WHERE field_id=72 AND object_id=" + articleId;
				ResultSet rs2 = OtrsSQL.dbExecuteQuery(ss2);
				while (rs2.next()) {
					field_72 = yyyyMMddHHmmss.parseDateTime(rs2.getString("value_date").substring(0, 19));
					
				}
				String ss3 = "SELECT value_date FROM dynamic_field_value WHERE field_id=73 AND object_id=" + articleId;
				ResultSet rs3 = OtrsSQL.dbExecuteQuery(ss3);
				while (rs3.next()) {
					field_73 = yyyyMMddHHmmss.parseDateTime(rs3.getString("value_date").substring(0, 19));
					
				}
				int secondsBetween = Seconds.secondsBetween(field_72, field_73).getSeconds();
				time = setTime(secondsBetween);
	
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return time;
	}
	
	
	
	private static String setTime(int secondsBetween) {
		String time = "";
		int days = (int)TimeUnit.SECONDS.toDays(secondsBetween);
		long hours = TimeUnit.SECONDS.toHours(secondsBetween) - (days * 24);
		long minutes = TimeUnit.SECONDS.toMinutes(secondsBetween) - (TimeUnit.SECONDS.toHours(secondsBetween) * 60 );
		long seconds = TimeUnit.SECONDS.toSeconds(secondsBetween) - (TimeUnit.SECONDS.toMinutes(secondsBetween) * 60 );
		time = days + "d " + hours + "h " + minutes + "m " + seconds + "s";	
		return time;
	}
}
