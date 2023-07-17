package MagnetoFramework.Luma.pageobject;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import MagnetoFramework.Luma.AbstractComponent.ExcelDataProvider;
import MagnetoFramework.Luma.AbstractComponent.Utility;

public class SignInPOC extends Utility {
	WebDriver driver;

	public SignInPOC(WebDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PAGEFACTORY
	@FindBy(xpath = "//div[@class='panel header'] //ul/li[2]")
	WebElement Sign_In_Link;
	@FindBy(xpath = "//input[@id='email']")
	WebElement EmailSign;
	@FindBy(xpath = "//input[@id='pass']")
	WebElement PasswordSign;
	@FindBy(xpath = "//div[@class='primary'] //button")
	WebElement SignInButton;
	@FindBy(xpath = "//div[@class='panel header'] //span[@class='logged-in']")
	WebElement welcomemsg;
	@FindBy(xpath = "//header/div[1]/div[1]/ul[1]/li[1]/span[1]")
	WebElement actWelcomeMsg;
	@FindBy(xpath = "(//button[@type='button'])[1]")
	WebElement DropDownSignOut;
	@FindBy(xpath = "//div[@aria-hidden='false']//a[normalize-space()='Sign Out']")
	WebElement SignOutButton;
	@FindBy(xpath = "//span[@class='base']")
	WebElement SignOutSuccessMsg;

	@FindBy(xpath = "//header/div[1]/div[1]")
	WebElement Sign_In_Success_Header;
	@FindBy(xpath = "//header/div[1]/div[1]/ul[1]/li[1]/span[1]")
	WebElement Default_welcome_msg;
	@FindBy(xpath = "//header/div[1]/div[1]/ul[1]/li[1]/span[1]")
	WebElement Actual_welcome_msg;
	@FindBy(css = ".message-error.error.message")
	WebElement error_Msg;

	By val = By.xpath("//*");

	// ACTION METHODS
	public void Sign_in_withvalidCredentials() {
		ExcelDataProvider excelDataProvider = new ExcelDataProvider();
		String[][] testData = excelDataProvider.getTestData();
		String email = testData[3][1];
		String password = testData[4][1];
		Sign_In_Link.click();
		EmailSign.sendKeys(email);
		PasswordSign.sendKeys(password);
		SignInButton.click();
	}

	public void Sign_in_Validation_Msg() {
		SoftAssert a = assertionsMethod();
		ExcelDataProvider excelDataProvider = new ExcelDataProvider();
		String[][] testData = excelDataProvider.getTestData();
		String ExpWelcomeMsg = testData[5][1];
		waitForElementToAppear(Sign_In_Success_Header);
		String DummyMsg = Default_welcome_msg.getText();
		System.out.println(DummyMsg);
		waitForElementToAppear(Sign_In_Success_Header);
		AllWaitstarslash();
		String actWelcomeMsg = Actual_welcome_msg.getText();
		a.assertEquals(actWelcomeMsg, ExpWelcomeMsg);
	}

	public void Sign_Out() {
		AllWaitstarslash();
		waitForElementToAppear(DropDownSignOut);
		DropDownSignOut.click();
		SignOutButton.click();
	}

	public void Sign_Out_validation() {
		SoftAssert a = assertionsMethod();
		ExcelDataProvider excelDataProvider = new ExcelDataProvider();
		String[][] testData = excelDataProvider.getTestData();
		String ExpSignOutMsg = testData[6][1];
		AllWaitstarslash();
		waitForElementToAppear(SignOutSuccessMsg);
		String ActSignOutMsg = SignOutSuccessMsg.getText();
		a.assertEquals(ActSignOutMsg, ExpSignOutMsg);
	}

	public void Sign_in_withinvalidCredentials() {
		ExcelDataProvider excelDataProvider = new ExcelDataProvider();
		String[][] testData = excelDataProvider.getTestData();
		String invalidemail = testData[7][1];
		String invalidpassword = testData[8][1];

		waitForElementToAppear(Sign_In_Success_Header);
		Sign_In_Link.click();
		EmailSign.sendKeys(invalidemail);
		PasswordSign.sendKeys(invalidpassword);
		SignInButton.click();

	}

	public void Sign_in_withinvalidCredentials_Screenshot() throws IOException {
		AllWaitstarslash();
		waitForElementToAppear(SignOutSuccessMsg);
		waitForElementToAppear(error_Msg);
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File("E://New folder//BDD//Luma//Screenshots//loginerror.png"));

	}

}
