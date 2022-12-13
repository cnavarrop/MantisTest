package utils;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import stepsDefinitions.Base;

public class ExcelData {

	public String path = System.getProperty("user.dir") + "\\src\\test\\resources\\Excel\\Credenciales.xlsx";
	public ExcelUtilities excel;

	@DataProvider(name = "dp")
	public Object[][] Data(Method m) {
		String hoja = m.getName();
		excel = new ExcelUtilities();
		excel.CargarExcel(hoja, path);
		int rows = excel.getRowCount();
		int cols = excel.getColCount();
		Object data[][] = new Object[rows - 1][1];
		Hashtable<String, String> tabla = null;

		for (int i = 1; i < rows; i++) {
			tabla = new Hashtable<String, String>();
			for (int j = 0; j < cols; j++) {

				tabla.put(excel.getDataCel(0, j), excel.getDataCel(i, j));
				data[i - 1][0] = tabla;
			}
		}

		return data;

	}

}
