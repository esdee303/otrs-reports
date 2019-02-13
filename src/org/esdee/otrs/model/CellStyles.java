package org.esdee.otrs.model;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CellStyles {
	
	public XSSFCellStyle csHeader(XSSFWorkbook workbook) {
		XSSFCellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontName("Roboto");
		font.setFontHeightInPoints((short) 10);
		font.setColor(new WebColors().honeydew);
		font.setBold(true);
		style.setFont(font);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(new WebColors().crayolaBlue);
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(new WebColors().dimGray);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(new WebColors().dimGray);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(new WebColors().dimGray);
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(new WebColors().dimGray);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.CENTER);
		return style;
	}
	
	public XSSFCellStyle columnStyle(XSSFWorkbook workbook, XSSFColor xssfColor) {
		XSSFCellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontName("Roboto");
		font.setFontHeightInPoints((short) 9);
		font.setColor(new WebColors().black);
		style.setFont(font);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(xssfColor);
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(new WebColors().dimGray);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(new WebColors().dimGray);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(new WebColors().dimGray);
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(new WebColors().dimGray);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.CENTER);
		return style;
	}
	
	public XSSFCellStyle csHeaderSubject(XSSFWorkbook workbook) {
		XSSFCellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontName("Roboto");
		font.setFontHeightInPoints((short) 10);
		font.setColor(new WebColors().honeydew);
		font.setBold(true);
		style.setFont(font);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(new WebColors().crayolaBlue);
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(new WebColors().dimGray);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(new WebColors().dimGray);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(new WebColors().dimGray);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.LEFT);
		return style;
	}
	
	public XSSFCellStyle subjectColumnStyle(XSSFWorkbook workbook, XSSFColor xssfColor) {
		XSSFCellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontName("Roboto");
		font.setFontHeightInPoints((short) 9);
		font.setColor(new WebColors().black);
		style.setFont(font);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(xssfColor);
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(new WebColors().dimGray);
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(new WebColors().dimGray);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(new WebColors().dimGray);
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(new WebColors().dimGray);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setAlignment(HorizontalAlignment.LEFT);
		return style;
	}
}
