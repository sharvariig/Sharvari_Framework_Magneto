package MagnetoFramework.Luma.AbstractComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	private final String EXCEL_FILE_PATH = "E:\\New folder\\BDD\\Luma\\src\\test\\resources\\testdata\\testdata.xlsx";

	public String[][] getTestData() {
		String[][] testData = null;
		try (FileInputStream fis = new FileInputStream(new File(EXCEL_FILE_PATH));
				Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0); // Assuming data is present in the first sheet
			int rowCount = sheet.getLastRowNum() + 1;
			int columnCount = sheet.getRow(0).getLastCellNum();

			testData = new String[rowCount][columnCount];

			for (int i = 0; i < rowCount; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < columnCount; j++) {
					Cell cell = row.getCell(j);
					Object cellValue = getCellValue(cell);
					testData[i][j] = cellValue.toString();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return testData;
//		ExcelDataProvider excelDataProvider = new ExcelDataProvider();
//		String[][] testData = excelDataProvider.getTestData();
//		String url = testData[0][1];
//		String First_Name;= testData[1][1];
//		String Last_Name;= testData[2][1];
//		String email = testData[3][1];
//		String password = testData[4][1];
//		String ExpWelcomeMsg;= testData[5][1];
//		String ExpSignOutMsg;= testData[6][1];
//		String invalidemail;= testData[7][1];
//		String invalidpassword;= testData[8][1];
//		String ExpOrderPurchace;= testData[9][1];
	}

	private static Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue();
			} else {
				return cell.getNumericCellValue();
			}
		case BOOLEAN:
			return cell.getBooleanCellValue();
		case FORMULA:
			return cell.getCellFormula();
		case BLANK:
			return "";
		default:
			return "";
		}
	}

	public void Check() {
		String[][] testData = getTestData();

		for (int i = 0; i < testData.length; i++) {

			String key = testData[i][0];
			String value = testData[i][1];

			System.out.println("Key: " + key);
			System.out.println("Value: " + value);
			System.out.println();
		}
	}

}
