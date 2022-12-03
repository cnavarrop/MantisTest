package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



import com.aventstack.extentreports.Status;

import cl.local.base.Base;
import utils.ExtentManager;
import utils.ScreenShot;

public class Listeners extends ExtentManager implements ITestListener {
	
	String PathImg = System.getProperty("user.dir")+"\\src\\test\\resources\\screenShots\\";
			
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getName());
        
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName()+" finalizo correctamente");
			Base.log.info("Finalización correcta del caso de pruebas");
			try {
				String img = ScreenShot.screenShot(result.getName());
				test.addScreenCaptureFromPath(img);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test Fail");
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "Test Skipped");
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}

}
