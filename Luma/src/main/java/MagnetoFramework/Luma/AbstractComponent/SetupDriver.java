package MagnetoFramework.Luma.AbstractComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SetupDriver {
	WebDriver driver;

	public WebDriver initializeDriver() throws IOException {
		// properties class
		Properties pr = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\MagnetoFramework\\Luma\\resources\\GlobalData.properties");
		pr.load(fis);
		String browserName = pr.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions op = new ChromeOptions();

			System.setProperty("webdriver.chrome.driver", "E:\\New folder\\BDD\\Luma\\Drivers\\chromedriver.exe");
			op.addArguments("headless");
			driver = new ChromeDriver();// ChromeDriver(op);
		}

		if (browserName.contains("firefox")) {
			System.setProperty("webdriver.gecko.driver", "E:\\New folder\\BDD\\Luma\\Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		if (browserName.contains("edge")) {
			System.setProperty("webdriver.edge.driver", "E:\\New folder\\BDD\\Luma\\Drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		return driver;
	}

	public WebDriver launchApplication() throws IOException {
		driver = initializeDriver();
		driver.get("https://magento.softwaretestingboard.com/");
		return driver;
	}

	public void closeTheApplication() {
		driver.close();
	}

	public String captureScreenshot(String testName) throws Exception {
		
		WebDriver driver = initializeDriver();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File screenshotFile = ts.getScreenshotAs(OutputType.FILE);

		String scpath = System.getProperty("user.dir") + "//reports//" + testName + ".png";

		try {
			FileUtils.copyFile(screenshotFile, new File(scpath));
		} catch (IOException e) {
			System.out.println("capture fail" + e.getMessage());
			e.printStackTrace();
		}
		return scpath;
	}

}
