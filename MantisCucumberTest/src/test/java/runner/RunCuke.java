package runner;


import org.testng.annotations.AfterClass;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src\\test\\resources\\features", glue = {
		"stepsDefinitions" }, monochrome = true, plugin = {"html:target/cucumber-reports/report.html","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})

public class RunCuke extends AbstractTestNGCucumberTests {

	@AfterClass
	public void setUP() {
		
         
	}

}
