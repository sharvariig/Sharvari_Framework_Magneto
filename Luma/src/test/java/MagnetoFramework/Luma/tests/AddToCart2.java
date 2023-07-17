package MagnetoFramework.Luma.tests;

import org.testng.annotations.Test;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import MagnetoFramework.Luma.AbstractComponent.SetupDriver;
import MagnetoFramework.Luma.extentreports.ExtentReport;
import MagnetoFramework.Luma.pageobject.AddToCartPOC;
import MagnetoFramework.Luma.pageobject.AddToCartPOC2;
import MagnetoFramework.Luma.pageobject.AddToCartPOC3;
import MagnetoFramework.Luma.pageobject.AddToCartPOC4;
import MagnetoFramework.Luma.pageobject.SignInPOC;


public class AddToCart2 {

	@Test
	public void product_Purchase_AddToCart() throws IOException {

		SetupDriver st = new SetupDriver();
		WebDriver driver = st.launchApplication();
		AddToCartPOC atcp = new AddToCartPOC(driver);
		AddToCartPOC2 atcp2 = new AddToCartPOC2(driver);
		AddToCartPOC3 atcp3 = new AddToCartPOC3(driver);
		AddToCartPOC4 atcp4 = new AddToCartPOC4(driver);
		SignInPOC sip = new SignInPOC(driver);
//		ExtentReport er=new ExtentReport();
//		ExtentReports ext=er.setupReport();
//		ext.createTest("End to End Add to cart");
		

		/* 1. Sign_in */
		sip.Sign_in_withvalidCredentials();

		/* 2.Scroll down and add any item from What’s new section */
		atcp.add_Item_From_Whats_New_Section();

		/*
		 * 3.Open the new tab from the same page and Navigate to Gear and Watches //
		 * switching to next tab
		 */
		atcp.gear_Watch_MouseHover_();

		/*
		 * 4. Select Price $40.00 - $49.99 filter from price option // scroll to see
		 * material option // 5. Select Material Rubber filter from material. // 6. Add
		 * the any product listed after filter
		 */
		atcp.price_filter();

		/* to take screenshot scroll up. */
		atcp2.scroll_Screenshot();

		/*
		 * now i want the control of driver into parent tab. refresh the Default Tab
		 */
		driver.navigate().refresh();

		/*
		 * 9. Navigate to Men’s section 10. Navigate to Bottoms and then pants. 11. Sort
		 * the Items by lowest price.
		 */
		atcp2.navigate_Mens_Bottom_Pants();
		atcp2.sort_Pant_Lowest_Price();

		/*
		 * 12. Add the First Item in the cart Waist size 34 and color Green.
		 * 13. Add to cart.
		 */
		atcp2.buy_Pant_34_Green();

		/* 14. Navigate to cart. */
		atcp2.Navigate_to_cart();

		/* 15. Increase the quantity of the pants from 1 to 4. */
		atcp3.Increase_The_Pant_Quantity();

		/*
		 * 16. Assert total Cart value and click on the checkout.
		 */
		atcp3.Assert_Total_Cart_Value();

		/*
		 * 17. Fill the required details on the checkout page // 18. Select the Table
		 * rate Radio button and Click on the Next button.
		 */
		atcp3.checkout_Page_Radio_Button();

		/*
		 * 19. Validate the Cart value with Step no 15. 
		 * 20. Click on the place order and validate the Thank you message and Order number.
		 */
		atcp4.place_Order();

		/* close the browser*/
		st.closeTheApplication();
//		ext.flush();

	}
}