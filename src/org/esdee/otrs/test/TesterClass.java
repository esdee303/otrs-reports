package org.esdee.otrs.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.esdee.otrs.model.*;




@SuppressWarnings("unused")
public class TesterClass {
	
	
	private static String FILEPATH = "C:\\eclipse-out\\";
	
	public TesterClass(List<User> userList) {
		
	}
	
	public static void main(String[] args) {
		List<User> userList = new ArrayList<User>();
		addUsers(userList);
		
		TesterClass testerClass = new TesterClass(userList);
			
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("colors");
		sheet.setColumnWidth(0, 4800);
		sheet.setColumnWidth(1, 4800);
		sheet.setColumnWidth(2, 9600);
		
		String fileName = "webcolors.xlsx";
		String[][] colors = new String[218][3];
		colors[0][0] = "COLOR NAME";
		colors[0][1] = "RGB VALUES";
		colors[0][2] = "COLOR";
		
		
		List<XSSFColor> webColors = new WebColors().xssfColorList();
		
		try(BufferedReader br = new BufferedReader(new FileReader("C:\\eclipse-cred\\clorlist.txt"))) {
			int counter = 0;
			String color;
			while((color = br.readLine()) != null) {
				colors[counter + 1][0] =  color.substring(0, color.indexOf("=") - 1);
				colors[counter + 1][1] = color.substring(color.lastIndexOf("(") + 1, color.indexOf(")"));
				colors[counter + 1][2] = " ";
				counter++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(Arrays.deepToString(colors));
		
		int rowNum = 0;
		int fillColorIndex = 0;
		for (String[] s : colors) {
			XSSFRow row = sheet.createRow(rowNum++);
			int colNum = 0;
			
			for(Object field : s) {
				XSSFCell cell = row.createCell(colNum++);
		/**		if (colNum == colors[0].length && rowNum > 1) {
					cell.setCellStyle(new CellStyles().rightColumnStyle(workbook, webColors.get(fillColorIndex)));
					fillColorIndex++;
				} else if (colNum == colors[0].length - 1){
					cell.setCellStyle(new CellStyles().middleColumnStyle(workbook, new WebColors().white));
				} else {
					cell.setCellStyle(new CellStyles().leftColumnStyle(workbook, new WebColors().white));
				}*/
				if(field instanceof String) {
					cell.setCellValue((String) field);
					int rowIndex = cell.getRowIndex();
					XSSFRow rowR = sheet.getRow(rowIndex);
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
				}
			}
		}
		
		try {
			String epoch = String.valueOf(Instant.now().getEpochSecond());
			FileOutputStream fos = new FileOutputStream(FILEPATH + epoch + "_" + fileName);
			workbook.write(fos);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		/**	Ticket ticket = Ticket.getTicketWithTn(1040656);
		System.out.println(ticket.toString());
		List<User> userList = new ArrayList<User>();
		userList.add(new User(5)); userList.add(new User(7)); userList.add(new User(9));
		userList.add(new User(15)); userList.add(new User(25)); userList.add(new User(26));
		userList.add(new User(30)); userList.add(new User(32)); userList.add(new User(33));
		userList.add(new User(42)); userList.add(new User(53)); userList.add(new User(54));
		userList.add(new User(56)); userList.add(new User(57)); userList.add(new User(58));
		*/
		
	}
	
	private static void addUsers(List<User> userList) {
		userList.add(new User(5)); userList.add(new User(7)); userList.add(new User(9));
		userList.add(new User(15)); userList.add(new User(25)); userList.add(new User(26));
		userList.add(new User(30)); userList.add(new User(32)); userList.add(new User(33));
		userList.add(new User(42)); userList.add(new User(53)); userList.add(new User(54));
		userList.add(new User(56)); userList.add(new User(57)); userList.add(new User(58));
	}
	
	private static void addWebColors(List<XSSFColor> webColors) {
		webColors.add(new WebColors().white);
		webColors.add(new WebColors().pink);
		webColors.add(new WebColors().lightPink);
		webColors.add(new WebColors().hotPink);
		webColors.add(new WebColors().deepPink);
		webColors.add(new WebColors().paleVioletRed);
		webColors.add(new WebColors().mediumVioletRed);
		webColors.add(new WebColors().lightSalmon);
		webColors.add(new WebColors().salmon);
		webColors.add(new WebColors().darkSalmon);
		webColors.add(new WebColors().lightCoral);
		webColors.add(new WebColors().indianRed);
		webColors.add(new WebColors().crimson);
		webColors.add(new WebColors().firebrick);
		webColors.add(new WebColors().darkRed);
		webColors.add(new WebColors().red);
		webColors.add(new WebColors().alizarinCrimson);
		webColors.add(new WebColors().amaranth);
		webColors.add(new WebColors().amarantPink);
		webColors.add(new WebColors().radicalRed);
		webColors.add(new WebColors().amaranthPurple);
		webColors.add(new WebColors().amaranthMP);
		webColors.add(new WebColors().bloodRed);
		webColors.add(new WebColors().burgundy);
		webColors.add(new WebColors().candyAppleRed);
		webColors.add(new WebColors().candyPink);
		webColors.add(new WebColors().cardinalRed);
		webColors.add(new WebColors().fresnoStateCardinal);
		webColors.add(new WebColors().uscCardinalRed);
		webColors.add(new WebColors().stanfordRed);
		webColors.add(new WebColors().carmineRed);
		webColors.add(new WebColors().wildWatermelonRed);
		webColors.add(new WebColors().ceriseRed);
		webColors.add(new WebColors().hollywoodCerise);
		webColors.add(new WebColors().crayolaCerise);
		webColors.add(new WebColors().irresistibleRed);
		webColors.add(new WebColors().munsellRed);
		webColors.add(new WebColors().pantoneRed);
		webColors.add(new WebColors().crayolaRed);
		webColors.add(new WebColors().scarletRed);
		webColors.add(new WebColors().imperialRed);
		webColors.add(new WebColors().rubyRed);
		webColors.add(new WebColors().rustyRed);
		webColors.add(new WebColors().fireEnginRed);
		webColors.add(new WebColors().chiliRed);
		webColors.add(new WebColors().cornellRed);
		webColors.add(new WebColors().madderRed);
		webColors.add(new WebColors().redwoodRed);
		webColors.add(new WebColors().chocolateCosmosRed);
		webColors.add(new WebColors().turkeyRed);
		webColors.add(new WebColors().cinnabarRed);
		webColors.add(new WebColors().coquelicotRed);
		webColors.add(new WebColors().mahoganyRed);
		webColors.add(new WebColors().raspberryRed);
		webColors.add(new WebColors().venetianRed);
		webColors.add(new WebColors().vermilionRed);
		webColors.add(new WebColors().rossoCorsa);
		webColors.add(new WebColors().orangeRed);
		webColors.add(new WebColors().tomato);
		webColors.add(new WebColors().coral);
		webColors.add(new WebColors().darkOrange);
		webColors.add(new WebColors().orange);
		webColors.add(new WebColors().yellow);
		webColors.add(new WebColors().lightYellow);
		webColors.add(new WebColors().lemonChiffon);
		webColors.add(new WebColors().lightGoldenrodYellow);
		webColors.add(new WebColors().papayaWhip);
		webColors.add(new WebColors().moccasin);
		webColors.add(new WebColors().peachPuff);
		webColors.add(new WebColors().paleGoldenrod);
		webColors.add(new WebColors().khaki);
		webColors.add(new WebColors().darkKakhi);
		webColors.add(new WebColors().gold);
		webColors.add(new WebColors().cornsilk);
		webColors.add(new WebColors().blanchedAlmond);
		webColors.add(new WebColors().bisque);
		webColors.add(new WebColors().navajoWhite);
		webColors.add(new WebColors().wheat);
		webColors.add(new WebColors().burlywood);
		webColors.add(new WebColors().tan);
		webColors.add(new WebColors().rosyBrown);
		webColors.add(new WebColors().sandyBrown);
		webColors.add(new WebColors().goldenrod);
		webColors.add(new WebColors().darkGoldenrod);
		webColors.add(new WebColors().peru);
		webColors.add(new WebColors().chocolate);
		webColors.add(new WebColors().saddleBrown);
		webColors.add(new WebColors().sienna);
		webColors.add(new WebColors().brown);
		webColors.add(new WebColors().maroon);
		webColors.add(new WebColors().darkOliveGreen);
		webColors.add(new WebColors().olive);
		webColors.add(new WebColors().oliveDrab);
		webColors.add(new WebColors().yellowGreen);
		webColors.add(new WebColors().limeGreen);
		webColors.add(new WebColors().lime);
		webColors.add(new WebColors().lawnGreen);
		webColors.add(new WebColors().chartreuse);
		webColors.add(new WebColors().greenYellow);
		webColors.add(new WebColors().springGreen);
		webColors.add(new WebColors().mediumSpringGreen);
		webColors.add(new WebColors().lightGreen);
		webColors.add(new WebColors().paleGreen);
		webColors.add(new WebColors().darkSeaGreen);
		webColors.add(new WebColors().mediumAquamarine);
		webColors.add(new WebColors().mediumSeaGreen);
		webColors.add(new WebColors().seaGreen);
		webColors.add(new WebColors().forestGreen);
		webColors.add(new WebColors().green);
		webColors.add(new WebColors().darkGreen);
		webColors.add(new WebColors().aqua);
		webColors.add(new WebColors().cyan);
		webColors.add(new WebColors().lightCyan);
		webColors.add(new WebColors().paleTurquoise);
		webColors.add(new WebColors().aquaMarine);
		webColors.add(new WebColors().turquoise);
		webColors.add(new WebColors().mediumTurqoise);
		webColors.add(new WebColors().darkTurquoise);
		webColors.add(new WebColors().lightSeaGreen);
		webColors.add(new WebColors().airForceBlue);
		webColors.add(new WebColors().spaceCadetBlue);
		webColors.add(new WebColors().fluorescentBlue);
		webColors.add(new WebColors().azureWhite);
		webColors.add(new WebColors().babyBlue);
		webColors.add(new WebColors().blueDeFrance);
		webColors.add(new WebColors().blueGray);
		webColors.add(new WebColors().bondiBlue);
		webColors.add(new WebColors().argentinianBlue);
		webColors.add(new WebColors().byzantineBlue);
		webColors.add(new WebColors().capriBlue);
		webColors.add(new WebColors().carolinaBlue);
		webColors.add(new WebColors().celesteBlue);
		webColors.add(new WebColors().ceruleanBlue);
		webColors.add(new WebColors().cobaltBlue);
		webColors.add(new WebColors().columbiaBlue);
		webColors.add(new WebColors().denimBlue);
		webColors.add(new WebColors().dukeBlue);
		webColors.add(new WebColors().egyptianBlue);
		webColors.add(new WebColors().electricBlue);
		webColors.add(new WebColors().morningBlue);
		webColors.add(new WebColors().glacousBlue);
		webColors.add(new WebColors().electricIndigoBlue);
		webColors.add(new WebColors().iceBlue);
		webColors.add(new WebColors().marianBlue);
		webColors.add(new WebColors().mayaBlue);
		webColors.add(new WebColors().neonBlue);
		webColors.add(new WebColors().crayolaBlue);
		webColors.add(new WebColors().ruddyBlue);
		webColors.add(new WebColors().spanishBlue);
		webColors.add(new WebColors().libertyBlue);
		webColors.add(new WebColors().delftBlue);
		webColors.add(new WebColors().duckBlue);
		webColors.add(new WebColors().resolutionBlue);
		webColors.add(new WebColors().polynesianBlue);
		webColors.add(new WebColors().independenceBlue);
		webColors.add(new WebColors().cadetBlue);
		webColors.add(new WebColors().darkCyan);
		webColors.add(new WebColors().teal);
		webColors.add(new WebColors().lightSteelBlue);
		webColors.add(new WebColors().powderBlue);
		webColors.add(new WebColors().lightBlue);
		webColors.add(new WebColors().skyBlue);
		webColors.add(new WebColors().lightSkyBlue);
		webColors.add(new WebColors().deepSkyBlue);
		webColors.add(new WebColors().dodgerBlue);
		webColors.add(new WebColors().cornflowerBlue);
		webColors.add(new WebColors().steelBlue);
		webColors.add(new WebColors().royalBlue);
		webColors.add(new WebColors().blue);
		webColors.add(new WebColors().mediumBlue);
		webColors.add(new WebColors().darkBlue);
		webColors.add(new WebColors().navy);
		webColors.add(new WebColors().midnightBlue);
		webColors.add(new WebColors().lavender);
		webColors.add(new WebColors().thistle);
		webColors.add(new WebColors().plum);
		webColors.add(new WebColors().violet);
		webColors.add(new WebColors().orchid);
		webColors.add(new WebColors().fuchsia);
		webColors.add(new WebColors().magenta);
		webColors.add(new WebColors().mediumOrchid);
		webColors.add(new WebColors().mediumPurple);
		webColors.add(new WebColors().blueViolet);
		webColors.add(new WebColors().darkViolet);
		webColors.add(new WebColors().darkOrchid);
		webColors.add(new WebColors().darkMagenta);
		webColors.add(new WebColors().purple);
		webColors.add(new WebColors().indigo);
		webColors.add(new WebColors().darkSlateBlue);
		webColors.add(new WebColors().slateBlue);
		webColors.add(new WebColors().mediumSlateBlue);
		webColors.add(new WebColors().white);
		webColors.add(new WebColors().snow);
		webColors.add(new WebColors().honeydew);
		webColors.add(new WebColors().mintCream);
		webColors.add(new WebColors().azure);
		webColors.add(new WebColors().aliceBlue);
		webColors.add(new WebColors().ghostWhite);
		webColors.add(new WebColors().whiteSmoke);
		webColors.add(new WebColors().seashell);
		webColors.add(new WebColors().beige);
		webColors.add(new WebColors().oldLace);
		webColors.add(new WebColors().floralWhite);
		webColors.add(new WebColors().ivory);
		webColors.add(new WebColors().antiqueWhite);
		webColors.add(new WebColors().linen);
		webColors.add(new WebColors().lavenderBlush);
		webColors.add(new WebColors().mistyRose);
		webColors.add(new WebColors().gainsboro);
		webColors.add(new WebColors().lightGray);
		webColors.add(new WebColors().silver);
		webColors.add(new WebColors().darkGray);
		webColors.add(new WebColors().gray);
		webColors.add(new WebColors().dimGray);
		webColors.add(new WebColors().lightSlateGray);
		webColors.add(new WebColors().slateGray);
		webColors.add(new WebColors().darkSlateGray);
		webColors.add(new WebColors().black);
	}
	
	
}
