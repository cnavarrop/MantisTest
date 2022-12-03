package cl.local.test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import cl.local.base.Base;

public class ProbarLinksMiVista extends Base{
	
	@Test
	public void LinksMiVista()
	{
		LogInMantis(prop.getProperty("user"), prop.getProperty("pass"));
		driver.findElement(By.xpath("//*[@id=\"sidebar\"]/ul/li[1]/a")).click();
		probarLinks();
	}

}
