package org.esdee.otrs.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@SuppressWarnings("unused")
public class SMTPSender {
	private static final String CRED_FILE = "c:\\eclipse-cred\\reportmailer_agents.txt";
	final static DateTimeFormatter DT = DateTimeFormat.forPattern("yyyy-MM-dd");
	private String line;
	private DateTime today = new DateTime();
	private DateTime weekStart = today.dayOfWeek().withMinimumValue();
	private String userName = "";
	private String password = "";
		
	public SMTPSender(String filePath, String fileName, List<String> recipients) throws IOException, InterruptedException {
		
		ImageHtmlEmail email = new ImageHtmlEmail();
		EmailAttachment agentsActivity = new EmailAttachment();
		
		BufferedReader br = new BufferedReader(new FileReader(CRED_FILE));
		while ((line = br.readLine()) != null) {
			if (line.contains("userName")) {
				userName = line.substring(line.indexOf("=") + 1, line.length());
			} else if (line.contains("password")) {
				password = line.substring(line.indexOf("=") + 1, line.length());
				email.setAuthenticator(new DefaultAuthenticator(userName, password));
			} else if (line.contains("eMailAddressFrom")) {
				try {
					email.setFrom(line.substring(line.indexOf("=") + 1, line.length()), "OTRS Activity Tracker");
				} catch (EmailException e) {
					e.printStackTrace();
				}
			} else if (line.contains("smtpHostName")) {
				email.setHostName(line.substring(line.indexOf("=") + 1, line.length()));
			} else if (line.contains("smtpPort")) {
				email.setSmtpPort(Integer.valueOf(line.substring(line.indexOf("=") + 1, line.length())));
			}
			
		}
		br.close();
		
		//URL url = new URL("http://www.apache.org");
		agentsActivity.setPath(filePath + fileName);
		agentsActivity.setDisposition(EmailAttachment.ATTACHMENT);
		agentsActivity.setDescription(fileName);
		agentsActivity.setName(fileName);
		
		// sendMail(email);
		
		try {
			//email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setStartTLSEnabled(true);
			email.setStartTLSEnabled(true);
			for(String recipient : recipients) email.addTo(recipient);
			email.setSubject("OTRS-Agent-Activities " + DT.print(weekStart) + " - " + DT.print(weekStart.plusDays(6)));
			email.attach(agentsActivity);
			email.setHtmlMsg("<html><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><meta charset=\"UTF-8\"><body><p style=\"font-family:ebrima;\">"
					+ "Attached to this mail:</p><h3 style=\"font-family:ebrima;\">Weekly overview of Agents OTRS Activity</h3> "
					+ "<p style=\"font-family:ebrima;\">" + fileName + "</p>");
			email.setTextMsg("Your email client does not support HTML messages");
			email.setDebug(true);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMail(ImageHtmlEmail email) {
		
	}
}

