package MagnetoFramework.Luma.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;
import MagnetoFramework.Luma.AbstractComponent.ExcelDataProvider;

import MagnetoFramework.Luma.AbstractComponent.Utility;

public class AddToCartPOC4 extends Utility {
	WebDriver driver;

	public AddToCartPOC4(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PAGEFACTORY
	@FindBy(xpath = "//div[@class='opc-block-summary']")
	WebElement summary;
	@FindBy(xpath = "//div[@class='opc-wrapper']")
	WebElement wrapper;
	@FindBy(xpath = "//div[@class='billing-address-details']")
	WebElement billingAddressDetails;
	@FindBy(xpath = "//button[@title='Place Order']")
	WebElement Place_Order_Button;
	@FindBy(xpath = "//span[contains(text(),'Thank you for your purchase!')]")
	WebElement Place_Order_Thankyoumsg;
	@FindBy(xpath = "//span[contains(text(),'Thank you for your purchase!')]")
	WebElement Orderid_Loc;
	@FindBy(xpath = "//main[@id='maincontent']")
	WebElement mainContent;
	@FindBy(xpath = "//span[@class='base']")
	WebElement baseContent;
	@FindBy(xpath = "//body/div[1]/main[1]/div[1]")
	WebElement dkele;

	public void place_Order() {
		Actions actionss =actionsMethod();
		SoftAssert a =assertionsMethod();
		ExcelDataProvider excelDataProvider = new ExcelDataProvider();
		String[][] testData = excelDataProvider.getTestData();
		String ExpOrderPurchace = testData[9][1];
		waitForElementToAppear(summary);
		waitForElementToAppear(summary);
		waitForElementToAppear(wrapper);
		waitForElementToAppear(billingAddressDetails);
		waitForElementToAppear(Place_Order_Button);
		Place_Order_Button.click();

		// validate the Thank you message and Order number.
		waitForElementToAppear(dkele);
		String ActOrderPurchace = Place_Order_Thankyoumsg.getText();
		a.assertEquals(ActOrderPurchace, ExpOrderPurchace);
		System.out.println(ActOrderPurchace);
		waitForElementToAppear(Orderid_Loc);
		String Act_orderId = Orderid_Loc.getText();
		actionss.moveToElement(Orderid_Loc).click();

		// new page id collect
		waitForElementToAppear(mainContent);
		waitForElementToAppear(baseContent);
		String fullText = baseContent.getText();// Order # 000015392
		String Exp_orderId = fullText.replace("Order # ", "");
		a.assertEquals(Act_orderId, Exp_orderId);
		System.out.println(Act_orderId);
	}
}