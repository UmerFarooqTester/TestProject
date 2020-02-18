package learnagainpkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class softasserthardassert {
	WebDriver driver;
	@BeforeClass
	public void startapp() {
		System.setProperty("webdriver.chrome.driver", "E:\\Eclipse\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("application started, browser opened.");
		}
	@Test
	public void softassert() {
		SoftAssert assertion = new SoftAssert();
		System.out.println("Test 1 started.");
		assertion.assertEquals(12, 13);
		System.out.println("Test 1 completed.");		
	}
	
	@Test
	public void hardassert() {
		System.out.println("Test 2 started.");
		Assert.assertEquals(12, 13);
		System.out.println("Test 2 completed.");		
	}

	@AfterClass
	public void closeapp() {
		driver.quit();
		System.out.println("Browser closed");
	}
}
