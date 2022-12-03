package utils;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

public class ExcelData {
	String path = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\Credenciales.xlsx";

	@DataProvider(name = "dp")
	public Object[][] TestData(Method m) {
        String hoja = m.getName();
		ExcelUtilities excel = new ExcelUtilities(path, hoja);
		int row = excel.getRowCount();
		int col = excel.getColCount();
		Object data[][] = new Object[row-1][1];
        Hashtable<String, String> table = null;
		for (int i = 1; i <row; i++) {
			table = new Hashtable<String, String>();
			for (int j = 0; j < col; j++) {
			
				table.put(excel.getCellData(0, j), excel.getCellData(i, j));
            //String cellData = excel.getCellData(i, j);
            data[i-1][0]= table;
			}
		}
		
		return data;
	}

}
