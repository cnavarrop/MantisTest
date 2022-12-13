package pages;

import org.openqa.selenium.By;

import stepsDefinitions.Base;

public class HomePage extends Base {

	By buscar = By.xpath("//*[@id=\"nav-search\"]/form/span/input");

	public Boolean validarHomePage() {

		String title = "http://18.206.238.171:8989/my_view_page.php";
		if ((driver.getCurrentUrl().equalsIgnoreCase(title))) {
			return true;
		} else {
			return false;
		}
	}

	public void buscarIncidencia(String id) {

		driver.findElement(buscar).sendKeys(id);
	}

}
