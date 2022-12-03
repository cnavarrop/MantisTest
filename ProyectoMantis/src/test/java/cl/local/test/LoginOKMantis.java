package cl.local.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cl.local.base.Base;
import utils.ExcelData;

public class LoginOKMantis extends Base {

	String path = System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\Credenciales.xlsx";
	String hoja = "LoginOKMantis";
	ExcelData data = new ExcelData();

	@Test(dataProvider = "credenciales")
	public void loginOK(String user, String pass) {

		LogInMantis(user, pass);
		
	}

	@DataProvider(name = "credenciales")
	public Object[][] getDatos() {
		Object datos[][] = data.TestData(path, hoja);
		return datos;
	}
	
	
	

}
