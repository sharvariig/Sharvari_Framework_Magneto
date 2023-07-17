package MagnetoFramework.Luma.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReport {
	public ExtentReports setupReport() {

		String rpath = System.getProperty("user.dir")+"//reports//Sharvarireport.html";
		ExtentSparkReporter repo = new ExtentSparkReporter(rpath);
		repo.config().setReportName("Sharvari's Report");
		repo.config().setDocumentTitle("Luma Project Report");

		ExtentReports ext = new ExtentReports();
		ext.attachReporter(repo);
		ext.setSystemInfo("tester name", "Sharvari_Gaikwad");
		return ext;

	}
}
