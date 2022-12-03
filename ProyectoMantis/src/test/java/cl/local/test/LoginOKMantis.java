package cl.local.test;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cl.local.base.Base;
import utils.ExcelData;

public class LoginOKMantis extends Base {

	//ExcelData data = new ExcelData();

	@Test(dataProviderClass = ExcelData.class, dataProvider = "dp")
	public void loginOKMantis(Hashtable<String, String> data) {
		
        String usuario = data.get("Usuario");
        String pass = data.get("Contraseña");
		LogInMantis(usuario, pass);
		
	}

//	@DataProvider(name = "credenciales")
//	public Object[][] getDatos() {
//		Object datos[][] = data.TestData(path, hoja);
//		return datos;
//	}
	
	
	

}
