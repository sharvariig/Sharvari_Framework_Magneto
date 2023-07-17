package MagnetoFramework.Luma.pageobject;

import java.util.Iterator;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import MagnetoFramework.Luma.AbstractComponent.Utility;

public class AddToCartPOC extends Utility {
	WebDriver driver;

	public AddToCartPOC(WebDriver driver) {
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
	@FindBy(xpath = "//body[1]/div[1]/div[1]/div[1]/div[2]/nav[1]/ul[1]/li[1]/a[1]/span[1]")
	WebElement WhatsNew_Loc;
	@FindBy(xpath = "(//div/ol/li)[3]")
	WebElement WhatsNew_Product3_Loc;
	@FindBy(xpath = "(//div[@id='option-label-size-143-item-167'])[3]")
	WebElement WhatsNew_Product3_SizeS_Loc;
	@FindBy(xpath = "(//div[@id='option-label-color-93-item-50'])[1]")
	WebElement WhatsNew_Product3_ColourB_Loc;
	@FindBy(xpath = "(//span[contains(text(),'Add to Cart')])[3]")
	WebElement WhatsNew_Product3_AddtoCart_Loc;
	@FindBy(xpath = "//div[@id='store.menu']")
	WebElement Header_StoreMenu_Loc;
	@FindBy(xpath = "(//span[contains(text(),'Gear')])[1]")
	WebElement Header_Gear_Loc;
	@FindBy(xpath = "(//span[contains(text(),'Watches')])[1]")
	WebElement Header_Watches_Loc;
	@FindBy(xpath = "//main[@id='maincontent']")
	WebElement all_WatchList;
	@FindBy(xpath = "//div[@id='narrow-by-list']")
	WebElement filter_Options;
	@FindBy(xpath = "//div[@id='narrow-by-list'] //div //div[contains(text(),'Price')]")
	WebElement price_Filter;
	@FindBy(xpath = "//body/div[1]/main[1]/div[3]/div[2]")
	WebElement safer_price;
	@FindBy(xpath = "(//ol[@class='items'])[2]")
	WebElement safer_price_list;

	@FindBy(xpath = "(//div[@aria-hidden='false']//ol[@class='items'] //a)[1]")
	WebElement price_Range4049;
	@FindBy(xpath = "//div[contains(text(),'Material')]")
	WebElement material_Filter;
	@FindBy(xpath = "(//div[@aria-hidden='false']//ol[@class='items'] //li/a)[3]")
	WebElement material_Type_Rubber;
	@FindBy(xpath = "((//div[@class='product details product-item-details'])[1])")
	WebElement first_Watch_From_List;
	@FindBy(xpath = "(//span[contains(text(),'Add to Cart')])[1]")
	WebElement first_Watch_AddToCart;

	// ACTION METHODS
	public void add_Item_From_Whats_New_Section() {
		Actions actionss =actionsMethod();
		JavascriptExecutor jse = jseExecutorMethod(); 
		WhatsNew_Loc.click();
		jse.executeScript("window.scroll(0,1500)");
		actionss.moveToElement(WhatsNew_Product3_Loc).build().perform();
		actionss.moveToElement(WhatsNew_Product3_SizeS_Loc).click().build().perform();
		actionss.moveToElement(WhatsNew_Product3_ColourB_Loc).click().build().perform();
		actionss.moveToElement(WhatsNew_Product3_AddtoCart_Loc).click().build().perform();
	}

	public void gear_Watch_MouseHover_() {
		Actions actionss =actionsMethod();
		waitForElementToAppear(Header_StoreMenu_Loc);
		actionss.moveToElement(Header_Gear_Loc).build().perform();
		actionss.moveToElement(Header_Watches_Loc).build().perform();
		actionss.moveToElement(Header_Watches_Loc).keyDown(Keys.CONTROL).click().build().perform();
		actionss.keyUp(Keys.CONTROL).build().perform();

		Iterator<String> it = window_handle();
		String parentid = it.next();
		System.out.println(parentid);
		String childid = it.next();
		driver.switchTo().window(childid);

	}

	public void price_filter() {
		Actions actionss =actionsMethod();
		JavascriptExecutor jse = jseExecutorMethod(); 
		waitForElementToAppear(all_WatchList);
		waitForElementToAppear(filter_Options);
		waitForElementToAppear(price_Filter);
		waitForElementToAppear(safer_price);
		price_Filter.click();
		waitForElementToAppear(safer_price_list);
		price_Range4049.click();
		waitForElementToAppear(all_WatchList);
		waitForElementToAppear(filter_Options);

		jse.executeScript("window.scroll(0,400)");
		material_Filter.click();
		material_Type_Rubber.click();
		waitForElementToAppear(all_WatchList);
		waitForElementToAppear(first_Watch_From_List);
		jse.executeScript("window.scroll(0,500)");
		actionss.moveToElement(first_Watch_From_List).build().perform();
		first_Watch_AddToCart.click();
	}

}
