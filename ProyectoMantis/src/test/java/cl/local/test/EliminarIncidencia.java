package cl.local.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import cl.local.base.Base;

public class EliminarIncidencia extends Base {

	@Test

	public void eliminarIncidencia() {
		String xpathId = null;
		LogInMantis(prop.getProperty("user"), prop.getProperty("pass"));
		driver.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[2]/a")).click();
		List<String> id = ListaIncidencias();
		if (id == null || id.isEmpty()) {
           Assert.fail("No se encuentran incidencias declaradas");
		} else {
			for (String cod : id) {
				xpathId = "//td[@class='column-id']/a[text()='" + cod + "']";
				driver.findElement(By.xpath(xpathId)).click();
				driver.findElement(By.xpath(
						"/html/body/div[2]/div[2]/div[2]/div/div[1]/div/div[2]/div[2]/div/table/tfoot/tr/td/div/div[8]/form/fieldset/input[4]"))
						.click();
				driver.findElement(By.xpath("//*[@id=\"action-group-div\"]/form/div/div[2]/div[2]/input")).click();
			}
		}

	}

	public List<String> ListaIncidencias() {
		List<String> codigo = new ArrayList<String>();
		String id = null;
		List<WebElement> el = driver.findElements(By.xpath("//td[@class='column-id']/a"));
		Iterator<WebElement> ite = el.iterator();
		while (ite.hasNext()) {
			id = ite.next().getText();
			codigo.add(id);
		}
		return codigo;
	}

}
