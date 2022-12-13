package pages;

import org.openqa.selenium.By;

import stepsDefinitions.Base;

public class LoginPage extends Base {

	By Submit = By.xpath("//*[@id=\"login-form\"]/fieldset/input[2]");
	By menlogin = By.xpath("//*[@id=\"main-container\"]/div/div/div/div/div[4]/p");

	
	
	public boolean mensajeDeError() {
		
		String menOriginal= driver.findElement(menlogin).getText();
		String menEsperado = po.getProperty("ErrorLogin");
		
	   if(menOriginal!=menEsperado) {
			return true;
		}else {
		return false;
		}
	}

	public void ingresarUrlMantis() {
		driver.get(po.getProperty("urlAcceso"));

	}

	public PasswordPage ingresarUsuario(String usuario) {
		PasswordPage pp = new PasswordPage();
		if (usuario == null || usuario.equals("")) {
			driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("");
			String text = driver.findElement(By.xpath("//*[@id=\"main-container\"]/div/div/div/div/div[4]/p/text()"))
					.getText();

		} else {

			driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(usuario);
		}
		return pp;
	}

	public void clickSubmit() {
		driver.findElement(Submit).click();

	}

}
