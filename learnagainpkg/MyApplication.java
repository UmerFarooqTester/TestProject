package learnagainpkg;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class MyApplication {
	
	WebDriver driver;
	
	@Test
	public void startapp() {
		//getting the URL and checking whether the URL provided has been accessed or not?
		System.setProperty("webdriver.chrome.driver", "E:\\Eclipse\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://login.live.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("login"));		
	}
	
	@Test(dependsOnMethods="startapp")
	public void loginapp() {
		//Submitting the login credentials and checking whether the user has login the application or not?
		driver.findElement(By.xpath("//*[@id=\"i0281\"]/div[1]/div/div[1]/div[2]/div/div/div[2]/div[2]/div/div[2]/div")).sendKeys("farooq.u@hotmail.com");
		((WebElement) driver.findElements(By.xpath("//*[@id=\"idSIButton9\"]"))).click();
		driver.findElement(By.xpath("//*[@id=\"i0118\"]")).sendKeys("ABC123ssi");
		((WebElement) driver.findElements(By.xpath("//*[@id=\"idSIButton9\"]"))).click();
		boolean status = driver.findElement(By.xpath("//*[@id=\"uhfCatLogo\"]/span")).isDisplayed();
		Assert.assertTrue(status);
	}
	@Test(dependsOnMethods="loginapp")
	public void logoutapp() {
		//logging out from the application and verifying whether the user has logged out or not?
		((WebElement) driver.findElements(By.xpath("//*[@id=\"meControl\"]/div/div/div[2]/div/div/img"))).click();
		((WebElement) driver.findElements(By.xpath("//*[@id=\"msame_si1\"]/a"))).click();
		boolean statuse = driver.findElement(By.xpath("//*[@id=\"meControl\"]/div/div/div[1]")).isDisplayed();
		Assert.assertTrue(statuse);	
	}
	
}
