package MagnetoFramework.Luma.AbstractComponent;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class Utility {
	public WebDriver driver;

	public Utility(WebDriver driver) {
		this.driver = driver;
	}

	public void AllWaitstarslash() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*")));
	}

	public WebElement waitForElementToAppear(WebElement val) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(val));
		return val;
	}

	public void waitForAllEleList(List<WebElement> val) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(val));
	}

	public Iterator<String> window_handle() {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		return it;
	}

	public SoftAssert assertionsMethod() {
		SoftAssert a = new SoftAssert();
		return a;

	}

	public JavascriptExecutor jseExecutorMethod() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		return jse;
	}

	public Actions actionsMethod() {
		Actions actionss = new Actions(driver);
		return actionss;
	}
}
