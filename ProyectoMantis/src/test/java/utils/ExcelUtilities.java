package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	public static String pathProyecto;
	public static XSSFWorkbook wbook;
	public static XSSFSheet sheet;

	public ExcelUtilities(String path, String hoja) {

		try {
			FileInputStream file = new FileInputStream(new File(path));
			wbook = new XSSFWorkbook(file);
			sheet = wbook.getSheet(hoja);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getRowCount() {

		int rowCount = 0;

		rowCount = sheet.getPhysicalNumberOfRows();
		return rowCount;
	}

	public int getColCount() {
		int cellCount = 0;

		cellCount = sheet.getRow(0).getPhysicalNumberOfCells();
		return cellCount;

	}

	public String getCellData(int row, int col) {
		String cellData = null;

		cellData = sheet.getRow(row).getCell(col).getStringCellValue();
		return cellData;
	}



	public Integer getCellDataNumber(int row, int col) {
		int cellDataNumber = 0;

		cellDataNumber = (int) sheet.getRow(row).getCell(col).getNumericCellValue();
		return cellDataNumber;

	}
}