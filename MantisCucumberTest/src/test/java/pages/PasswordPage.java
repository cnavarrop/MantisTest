package pages;

import org.openqa.selenium.By;

import stepsDefinitions.Base;

public class PasswordPage extends Base {
	HomePage hp = new HomePage();
	By BtnSubmit = By.xpath("//*[@id=\"login-form\"]/fieldset/input[3]");

	public HomePage ingresarPassword(String pass) {
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(pass);
		return hp;

	}
	
	public HomePage clickSubmit() {
		
		driver.findElement(BtnSubmit).click();
		return hp;
		
	}

}
