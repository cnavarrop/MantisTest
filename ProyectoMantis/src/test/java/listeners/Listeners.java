package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import utils.ExtentManager;

public class Listeners extends ExtentManager implements ITestListener {

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getName());

	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test Passed");
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
