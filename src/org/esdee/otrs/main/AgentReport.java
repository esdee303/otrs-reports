package org.esdee.otrs.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.esdee.otrs.model.*;
import org.esdee.otrs.util.OtrsSQL;
import org.esdee.otrs.util.SMTPSender;

@SuppressWarnings("unused")
public class AgentReport {
	private static final DateTimeFormatter yyyyMMddHHmmss = DateTimeFormat.forPattern("yyyyMMddHHmmss");
	private static final DateTimeFormatter yyyy_MM_ddHHmmss = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	private static String FILEPATH = "C:\\eclipse-out\\";
	private static String epoch = String.valueOf(Instant.now().getEpochSecond());
	private static String fileName = "AgentsReport-" + epoch + ".xlsx";
	
	public static void main(String[] args) {
		try {
			AgentReport ar = new AgentReport();
		} catch (SQLException s) {
		} catch (ClassNotFoundException c) {
		} catch (IOException i) {
		} catch (InterruptedException n) {
		}
	}

	public AgentReport() throws SQLException, ClassNotFoundException, IOException, InterruptedException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		DateTime now = new DateTime();
		List<User> userList = new ArrayList<User>();
		userList.add(new User(5)); userList.add(new User(7)); userList.add(new User(9));
		userList.add(new User(15)); userList.add(new User(25)); userList.add(new User(26));
		userList.add(new User(30)); userList.add(new User(32)); userList.add(new User(33));
		userList.add(new User(53)); userList.add(new User(54)); userList.add(new User(57));
		userList.add(new User(58));
		
		for (User user : userList) {
			now = new DateTime();
			DateTime weekStart = new DateTime().dayOfWeek().withMinimumValue();
			DateTime weekBefore = weekStart.minusWeeks(1);
			XSSFSheet sheet = workbook.createSheet(user.getLogin());
			List<Article> articles = new ArrayList<Article>(Article.articlesForUser(user.getId(), weekStart));
			// List<Article> articles = new ArrayList<Article>(Article.articlesForUser(user.getId(), weekBefore));
			String[][] articlesArray = new String[articles.size() + 1][5];
			articlesArray[0][0] = "TIME";
			articlesArray[0][1] = "TICKET";
			articlesArray[0][2] = "TITLE";
			articlesArray[0][3] = "ACTIVITY TYPE";
			articlesArray[0][4] = "REGISTERED TIME";
			for (int i = 0; i < articles.size(); i++) {
				articlesArray[i + 1][0] = articles.get(i).getCreateTime();
				articlesArray[i + 1][1] = String.valueOf(articles.get(i).getTn());
				articlesArray[i + 1][2] = articles.get(i).getTitle();
				articlesArray[i + 1][3] = articles.get(i).getArticleType();
				articlesArray[i + 1][4] = articles.get(i).getTimeRegistration();
			}
			sheet.setColumnWidth(0, 6000);
			sheet.setColumnWidth(1, 6000);
			sheet.setColumnWidth(2, 24000);
			sheet.setColumnWidth(3, 6000);
			sheet.setColumnWidth(4, 6000);
			System.out.println(Arrays.deepToString(articlesArray));

			int numberOfCols = articlesArray[0].length;
			int rowNum = 0;
			for (String[] s : articlesArray) {
				XSSFRow row = sheet.createRow(rowNum++);
				int colNum = 0;
				for (Object field : s) {
					XSSFCell cell = row.createCell(colNum++);
					if (row.getRowNum() == 0) {
						if (colNum == 3) {
							cell.setCellStyle(new CellStyles().csHeaderSubject(workbook));
						} else {
							cell.setCellStyle(new CellStyles().csHeader(workbook));
						}
					}
					int c = cell.getRowIndex();
					Row rowC = sheet.getRow(c);
					rowC.setHeightInPoints(20);
					if (field instanceof String) {
						cell.setCellValue((String) field);
					} else if (field instanceof Integer) {
						cell.setCellValue((Integer) field);
					}
				}
			}
			setCellStyles(sheet, numberOfCols, "eMail out", new WebColors().fluorescentBlue);
			setCellStyles(sheet, numberOfCols, "Note Internal", new WebColors().lightBlue);
			setCellStyles(sheet, numberOfCols, "Time Registration", new WebColors().lightPink);
			setCellStyles(sheet, numberOfCols, "New Tel. Ticket", new WebColors().coral);
			setCellStyles(sheet, numberOfCols, "Note External", new WebColors().plum);
			setCellStyles(sheet, numberOfCols, "Phone out", new WebColors().gainsboro);
		}

		try {
			now = new DateTime();
			FileOutputStream fos = new FileOutputStream(FILEPATH + fileName);
			workbook.write(fos);
			workbook.close();
			ArrayList<String> recipients = new ArrayList<String>();
			recipients.add("sdeferme@quant.be");
			// recipients.add("sdams@quant.be");
			SMTPSender rm = new SMTPSender(FILEPATH, fileName, recipients);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Integer> getTnList(User user) {
		List<Integer> tnList = new ArrayList<Integer>();
		int id = user.getId();
		String ss = "SELECT tn FROM ticket WHERE responsible_user_id=" + id + " OR user_id=" + id + " ORDER BY tn ASC";
		ResultSet rs;
		try {
			rs = OtrsSQL.dbExecuteQuery(ss);
			while (rs.next()) {
				tnList.add(rs.getInt("tn"));
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {

			e.printStackTrace();
		}
		return tnList;
	}
	
	private void setCellStyles(XSSFSheet sheet, int numberOfCols, String match, XSSFColor color) {
		char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		Iterator<Row> rowIterator = sheet.rowIterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				int col = cell.getColumnIndex();
				if (cell.getStringCellValue().equals(match)) {
					for (int i = 0; i < numberOfCols; i++) {
						CellReference cr = new CellReference(String.valueOf(alphabet[i]).toUpperCase() + String.valueOf(cell.getRowIndex()));
						cell = row.getCell(cr.getCol());
						if(i == 2) {
							cell.setCellStyle(new CellStyles().subjectColumnStyle(sheet.getWorkbook(), color));
						} else {
							cell.setCellStyle(new CellStyles().columnStyle(sheet.getWorkbook(), color));
						}
					}
				}
			}
		}
	}
}
