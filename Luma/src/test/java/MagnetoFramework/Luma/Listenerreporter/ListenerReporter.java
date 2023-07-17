package MagnetoFramework.Luma.Listenerreporter;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import MagnetoFramework.Luma.AbstractComponent.SetupDriver;
import MagnetoFramework.Luma.extentreports.ExtentReport;

public class ListenerReporter extends SetupDriver implements ITestListener {
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport er = new ExtentReport();
		ExtentReports ext = er.setupReport();
		test = ext.createTest("Test Started for " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentReport er = new ExtentReport();
		ExtentReports ext = er.setupReport();
		test = ext.createTest(result.getMethod().getMethodName());
		test.log(Status.PASS, "test has been completed successfully");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentReport er = new ExtentReport();
		ExtentReports ext = er.setupReport();
		test = ext.createTest(result.getMethod().getMethodName());
		test.log(Status.FAIL, "test has been failed");
		test.fail(result.getThrowable());
		String Filepath = null;
		try {
			Filepath = captureScreenshot(result.getMethod().getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(Filepath, result.getMethod().getMethodName());
	}

	@Override
	public void onFinish(ITestContext context) {
		ITestListener.super.onFinish(context);
		ExtentReport er = new ExtentReport();
		ExtentReports ext = er.setupReport();
		ext.flush();
	}

}
