package MagnetoFramework.Luma.pageobject;

import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import MagnetoFramework.Luma.AbstractComponent.Utility;

public class AddToCartPOC3 extends Utility {
	WebDriver driver;

	public AddToCartPOC3(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PAGEFACTORY
	@FindBy(xpath = "//td")
	WebElement all_Prod_Edit_Cart;

	@FindBy(xpath = "(//input[@title='Qty'])[1]")
	WebElement all_Prod1_QTY;
	@FindBy(xpath = "(//input[@title='Qty'])[2]")
	WebElement all_Prod2_QTY;
	@FindBy(xpath = "(//input[@title='Qty'])[3]")
	WebElement all_Prod3_QTY;
	@FindBy(xpath = "//div[@id='cart-totals']")
	WebElement Estimate_Shipping_and_Tax;
	@FindBy(xpath = "//div[@id='cart-totals'] //strong/span")
	WebElement Order_Total;
	@FindBy(xpath = "//div[@id='cart-totals'] //strong/span")
	WebElement price;
	@FindBy(xpath = "//button[@data-role='proceed-to-checkout']")
	WebElement proceed_To_Checkout;
	@FindBy(xpath = "//div[@class='opc-block-summary']")
	WebElement Order_Summary;
	@FindBy(xpath = "//div[@class='opc-block-summary']")
	WebElement Shipping_Address;
	@FindBy(xpath = "//div[normalize-space()='Shipping Methods']")
	WebElement Shipping_Methods;
	@FindBy(xpath = "//div[@id='checkout-shipping-method-load']")
	WebElement Shipping_RBL;
	@FindBy(xpath = "//input[@name='ko_unique_1']")
	WebElement tableRate_RadioButton;
	@FindBy(xpath = "//div[@class='opc-wrapper']")
	WebElement Mid_Portion;
	@FindBy(xpath = "//button[@class='button action continue primary']")
	WebElement next_Button;
	@FindBy(xpath = "//div[@class='opc-block-summary']")
	WebElement order_Summary;
	@FindBy(xpath = "//div[@class='opc-block-summary'] //tbody/tr/td/strong")
	WebElement order_Summary_Value;


	// ACTION METHODS

	public void Increase_The_Pant_Quantity() {
		JavascriptExecutor jse = jseExecutorMethod(); 
		waitForElementToAppear(all_Prod_Edit_Cart);
		AllWaitstarslash();
		List<String> values = Arrays.asList("1", "1", "4");
		all_Prod1_QTY.clear();
		all_Prod1_QTY.sendKeys(values.get(0));
		all_Prod2_QTY.clear();
		all_Prod2_QTY.sendKeys(values.get(1));
		all_Prod3_QTY.clear();
		all_Prod3_QTY.sendKeys(values.get(2));
		AllWaitstarslash();
		driver.findElement(By.xpath("//span[contains(text(),'Update Shopping Cart')]")).click();
		AllWaitstarslash();
		waitForElementToAppear(all_Prod_Edit_Cart);
		jse.executeScript("window.scroll(0,450)");
	}

	public void Assert_Total_Cart_Value() {
		SoftAssert a =assertionsMethod();
		waitForElementToAppear(Estimate_Shipping_and_Tax);
		waitForElementToAppear(Order_Total);
		waitForElementToAppear(Order_Total);
		waitForElementToAppear(price);		
		String PreTotalVal = price.getText().substring(1); // Removes the first character (dollar sign)
		a.assertEquals(PreTotalVal, "198.60");
		AllWaitstarslash();	
		waitForElementToAppear(proceed_To_Checkout);
		proceed_To_Checkout.click();
		System.out.println("order total value is "+PreTotalVal);

	}

	public void checkout_Page_Radio_Button() {
		JavascriptExecutor jse = jseExecutorMethod(); 		
		waitForElementToAppear(Order_Summary);
		waitForElementToAppear(Shipping_Address);
		waitForElementToAppear(Shipping_Methods);
		waitForElementToAppear(Shipping_RBL);
		waitForElementToAppear(tableRate_RadioButton);
		jse.executeScript("window.scrollBy(0, 450);");
		tableRate_RadioButton.click();
		waitForElementToAppear(Order_Summary);
		waitForElementToAppear(Shipping_Address);
		waitForElementToAppear(Shipping_Methods);
		waitForElementToAppear(Shipping_RBL);
		waitForElementToAppear(next_Button);
		next_Button.click();

	}

	public void ReviewPayments_Validate_Cartvalue() {
		SoftAssert a=assertionsMethod();
		AllWaitstarslash();
		waitForElementToAppear(Order_Summary);
		waitForElementToAppear(order_Summary_Value);
		String currentTotalVal = order_Summary_Value.getText().substring(1);
		a.assertEquals(currentTotalVal, "198.60");
	}
}
