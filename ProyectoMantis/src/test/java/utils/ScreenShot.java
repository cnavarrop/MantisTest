package utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import cl.local.base.Base;

public class ScreenShot extends Base {

public static String screenShot(String NombreImagen) throws IOException
{
	TakesScreenshot ts= (TakesScreenshot) driver;
	File origen= ts.getScreenshotAs(OutputType.FILE);
	String destino = System.getProperty("user.dir")+"\\src\\test\\resources\\screenShots\\"+NombreImagen+".png";
	File dest = new File(destino);
	FileUtils.copyFile(origen, dest);
	
	return destino;
	
}
	
}
