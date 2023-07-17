package MagnetoFramework.Luma.pageobject;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import MagnetoFramework.Luma.AbstractComponent.Utility;

public class AddToCartPOC2 extends Utility {
	WebDriver driver;

	public AddToCartPOC2(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PAGEFACTORY
	@FindBy(xpath = "//div[@class='panel header'] //ul/li[2]")
	WebElement Sign_In_Link;
	@FindBy(xpath = "//body/div[1]/main[1]/div[2]/div[2]/div[1]/div[1]")
	WebElement added_ToCart_Success_Msg;
	@FindBy(xpath = "//div[@id='store.menu']")
	WebElement Header_StoreMenu_Loc;
	@FindBy(xpath = "//div[@class='header content']")
	WebElement Header_Content_Loc;
	@FindBy(xpath = "//span[contains(text(),'Men')]")
	WebElement Header_Men_Loc;
	@FindBy(xpath = "//a[@id='ui-id-18']")
	WebElement Header_Bottom_Loc;
	@FindBy(xpath = "//a[@id='ui-id-23']")
	WebElement Header_Pant_Loc;
	@FindBy(xpath = "//body[1]/div[1]/main[1]/div[3]/div[1]")
	WebElement pant_List;
	@FindBy(xpath = "(//select[@data-role='sorter'])[1]")
	WebElement sort_option;

	@FindBy(xpath = "//div/main/div[3]/div/div[3]/ol/li")
	List<WebElement> all_Pant_list;
	@FindBy(xpath = "//div[@class='column main'] //div[@aria-label='34']")
	List<WebElement> all_Pant_Size;
	@FindBy(xpath = "//div[@class='column main'] //div[@aria-label='Green']")
	List<WebElement> all_Pant_Green_Colour;
	@FindBy(xpath = "//div[@class='column main'] //li //button[@type='submit']")
	List<WebElement> all_Pant_AddToCart;

	@FindBy(xpath = "//div[@class='message-success success message']")
	WebElement pant_Buy_SuccessMsg;
	@FindBy(xpath = "//header/div[2]/div[1]/a[1]")
	WebElement add_To_Cart_Main;
	@FindBy(xpath = "//header/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[4]/ol[1]/li")
	WebElement add_To_Cart_AllProd;
	@FindBy(xpath = "//span[normalize-space()='View and Edit Cart']")
	WebElement View__Edit_Cart;
	@FindBy(xpath = "//div[@class='cart-container']")
	WebElement View__Edit_CartContainer;

	By val = By.xpath("//*");

	// ACTION METHODS
	public void scroll_Screenshot() throws IOException {
		JavascriptExecutor jse = jseExecutorMethod(); 
		jse.executeScript("window.scrollTo(0,0)");
		waitForElementToAppear(added_ToCart_Success_Msg);
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File("E://New folder//BDD//Luma//Screenshots//addedcart.png"));

		Iterator<String> it = window_handle();
		String parentid = it.next();
		String childid = it.next();
		System.out.println(childid);
		driver.close();
		driver.switchTo().window(parentid);
	}

	public void navigate_Mens_Bottom_Pants() {
		Actions actionss =actionsMethod();
		waitForElementToAppear(Header_StoreMenu_Loc);
		actionss.moveToElement(Header_Men_Loc).build().perform();
		actionss.moveToElement(Header_Bottom_Loc).build().perform();
		actionss.moveToElement(Header_Pant_Loc).click().build().perform();
	}

	public void sort_Pant_Lowest_Price() {
		waitForElementToAppear(pant_List);
		waitForElementToAppear(sort_option);
		sort_option.click();
		Select staticDropdown = new Select(sort_option);
		staticDropdown.selectByVisibleText("Price");
	}

	public void buy_Pant_34_Green() {
		Actions actionss =actionsMethod();
		JavascriptExecutor jse = jseExecutorMethod(); 
		jse.executeScript("window.scroll(0,600)");
		waitForElementToAppear(pant_List);
		waitForAllEleList(all_Pant_list);
		all_Pant_list.stream().findFirst().ifPresent(pant -> actionss.moveToElement(pant).build().perform());
		all_Pant_Size.stream().findFirst().ifPresent(size -> actionss.moveToElement(size).click().build().perform());
		all_Pant_Green_Colour.stream().findFirst()
				.ifPresent(colour -> actionss.moveToElement(colour).click().build().perform());
		all_Pant_AddToCart.stream().findFirst()
				.ifPresent(addToCart2 -> actionss.moveToElement(addToCart2).click().build().perform());

		jse.executeScript("window.scrollTo(0, 0);");
		// jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");for
		// bottom
//		wait.until(ExpectedConditions.visibilityOfAllElements(pant_Buy_SuccessMsg));
		actionss.sendKeys(Keys.F5).perform();

	}

	public void Navigate_to_cart() {		
		JavascriptExecutor jse = jseExecutorMethod(); 
		waitForElementToAppear(Header_Content_Loc);
		waitForAllEleList(all_Pant_list);
		waitForAllEleList(all_Pant_list);
		add_To_Cart_Main.click();
		waitForElementToAppear(add_To_Cart_AllProd);
		jse.executeScript("window.scroll(0,450)");
		View__Edit_Cart.click();
		jse.executeScript("window.scroll(0,400)");
		waitForElementToAppear(View__Edit_CartContainer);
	}

}
