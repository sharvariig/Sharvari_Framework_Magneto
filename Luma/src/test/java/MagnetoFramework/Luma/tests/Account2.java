package MagnetoFramework.Luma.tests;

import org.testng.annotations.Test;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import MagnetoFramework.Luma.AbstractComponent.SetupDriver;
import MagnetoFramework.Luma.extentreports.ExtentReport;
import MagnetoFramework.Luma.pageobject.AccoumtCreationPOC;

public class Account2 {

	@Test
	public void verifying_User_Account_Creation() throws IOException {
//		ExtentReport er=new ExtentReport();
//		ExtentReports ext=er.setupReport();
//		ext.createTest("Verifying user account creation");
		SetupDriver st = new SetupDriver();
		WebDriver driver = st.launchApplication();

		AccoumtCreationPOC acp = new AccoumtCreationPOC(driver);

		// Create new account
		acp.Create_your_own_Account();

		// take Screenshot
		acp.CYOA_Take_Screenshot();

		// close the browser
		st.closeTheApplication();
//		ext.flush();
	}
}
