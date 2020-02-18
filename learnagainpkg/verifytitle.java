package learnagainpkg;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class verifytitle {
	WebDriver driver;
	@Test
	public void verifytitletest() {
		System.setProperty("webdriver.chrome.driver", "E:\\\\Eclipse\\\\chromedriver\\\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://q.door.fund/");
		String title = driver.getTitle();
		String expectedtitle = "Door";
		Assert.assertEquals(title, expectedtitle);
		System.out.println("Title verified");
		driver.quit();
	}
}
