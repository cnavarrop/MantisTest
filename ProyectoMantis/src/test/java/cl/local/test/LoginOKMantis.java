package cl.local.test;

import java.util.Hashtable;

import org.testng.annotations.Test;

import cl.local.base.Base;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;
import utils.ExcelData;

public class LoginOKMantis extends Base {

	//ExcelData data = new ExcelData();
	
	String resultado = null;
	String notas = null;

	@Test(dataProviderClass = ExcelData.class, dataProvider = "dp")
	public void loginOKMantis(Hashtable<String, String> data) throws TestLinkAPIException {
		
        String usuario = data.get("Usuario");
        String pass = data.get("Contraseña");
        String testcase = this.getClass().getSimpleName();
        String proyecto = Base.prop.getProperty("testProject");
        String testplan = Base.prop.getProperty("testPlan");
        String build = Base.prop.getProperty("build");
        
        
		try {
			LogInMantis(usuario, pass);
			resultado = TestLinkAPIResults.TEST_PASSED;
			notas = "Caso ejecutado correctamente";
			
		} catch (Exception e) {
			resultado = TestLinkAPIResults.TEST_FAILED;
			notas = "Caso ejecutado con errores : " +e;
			e.printStackTrace();
		}
		finally {
			Base.reportResult(proyecto,testplan ,testcase ,build , notas, resultado);
		}
        
	}

//	@DataProvider(name = "credenciales")
//	public Object[][] getDatos() {
//		Object datos[][] = data.TestData(path, hoja);
//		return datos;
//	}
	
	
	

}
