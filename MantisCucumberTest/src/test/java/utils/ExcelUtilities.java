package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

	public XSSFWorkbook wbook;
	public XSSFSheet sheet;

	public void CargarExcel(String hoja, String path) {

		try {
			FileInputStream file = new FileInputStream(new File(path));
			wbook = new XSSFWorkbook(file);
			sheet = wbook.getSheet(hoja);
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Integer getRowCount() {
		
		int rows = 0;
		rows = sheet.getPhysicalNumberOfRows();
		return rows;
	}
	
	public Integer getColCount() {
		
		int cells = 0;
		cells = sheet.getRow(0).getPhysicalNumberOfCells();
		return cells;
		
	}
	
	public String getDataCel(int row, int cell) {
		
		String cellData = "";
		
		cellData = sheet.getRow(row).getCell(cell).getStringCellValue();
		return cellData;
		
	}
	
	public Integer getCellDataNumber(int row, int col) {
		int cellDataNumber = 0;

		cellDataNumber = (int) sheet.getRow(row).getCell(col).getNumericCellValue();
		return cellDataNumber;

	}
}
