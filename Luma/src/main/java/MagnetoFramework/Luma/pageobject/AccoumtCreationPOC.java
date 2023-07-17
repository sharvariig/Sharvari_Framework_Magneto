package MagnetoFramework.Luma.pageobject;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import MagnetoFramework.Luma.AbstractComponent.ExcelDataProvider;
import MagnetoFramework.Luma.AbstractComponent.Utility;

public class AccoumtCreationPOC extends Utility {
	WebDriver driver;

	public AccoumtCreationPOC(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PAGEFACTORY
	@FindBy(xpath = "//header/div[1]/div[1]/ul[1]/li[3]/a[1]")
	WebElement CreateanAccount_Loc;
	@FindBy(xpath = "//input[@id='firstname']")
	WebElement First_Name_Loc;
	@FindBy(xpath = "//input[@id='lastname']")
	WebElement Last_Name_Loc;
	@FindBy(xpath = "//input[@id='is_subscribed']")
	WebElement Sign_Up_for_Newsletter_Loc;
	@FindBy(xpath = "//input[@id='email_address']")
	WebElement Email_Loc;
	@FindBy(xpath = "//input[@id='password']")
	WebElement Password_Loc;
	@FindBy(xpath = "//input[@id='password-confirmation']")
	WebElement Confirm_Password_Loc;
	@FindBy(xpath = "//button[@title='Create an Account']//span[contains(text(),'Create an Account')]")
	WebElement Create_an_Account_Loc;

	@FindBy(xpath = "//div[@class='message-error error message']")
	WebElement Account_Already_Created_EM;

	// ACTION METHODS
	public void Create_your_own_Account() {
		ExcelDataProvider excelDataProvider = new ExcelDataProvider();
		String[][] testData = excelDataProvider.getTestData();
		String First_Name = testData[1][1];
		String Last_Name = testData[2][1];
		String email = testData[3][1];
		String password = testData[4][1];

		CreateanAccount_Loc.click();
		First_Name_Loc.sendKeys(First_Name);
		Last_Name_Loc.sendKeys(Last_Name);
		Sign_Up_for_Newsletter_Loc.click();
		Email_Loc.sendKeys(email);
		Password_Loc.sendKeys(password);
		Confirm_Password_Loc.sendKeys(password);
		Create_an_Account_Loc.click();
		System.out.println("Account Successfully Created");
	}

	public void CYOA_Take_Screenshot() throws IOException {
		WebElement element = waitForElementToAppear(Account_Already_Created_EM);
		if (element != null) { 
			// "Account_Already_Created_EM" element is visible
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("E://New folder//BDD//Luma//Screenshots//Already_Account_Created.png"));
			System.out.println("Account_Already_Created & Capture the screenshot Successful");
		} else {
			// "Account_Already_Created_EM" element is not visible
			// when new account created
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("E://New folder//BDD//Luma//Screenshots//AcountCreated.png"));
			System.out.println("new account created. Capture the screenshot Successful");
		}
	}
}
