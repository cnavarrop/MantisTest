package utils;

import java.io.File;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	public static ExtentReports getIntance()
	{
		if(extent == null) {
			extent = new ExtentReports(System.getProperty("user.dir")+ "\\test-output\\html\\extentReports.html", true, DisplayOrder.OLDEST_FIRST);
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\extentConfig\\ExtentReportConfig.xml" ));
		}
		
		return extent;
	}

}	
