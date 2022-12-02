package cl.local.test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import cl.local.base.Base;

public class LoginFailMantis extends Base{
	
	@Test
	public void loginClaveIncorrecta() {

		LogInMantis(prop.getProperty("user"), "PassErronea");
		String alert = driver.findElement(By.xpath(prop.getProperty("alertLogin"))).getText();
		if (driver.findElement(By.xpath(prop.getProperty("alertLogin"))).isDisplayed()) {
			Reporter.log(alert);
		}
		Reporter.log("Prueba Finalizada correctamente");
		driver.close();

	}

}
