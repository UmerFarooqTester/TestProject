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
public class doorapp {
	WebDriver driver;	
	@BeforeClass
	public void startbrowser() {
		System.setProperty("webdriver.chrome.driver", "E:\\Eclipse\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("browser opened");
	}
	@Test
	public void startapp() {
		//getting the URL and checking whether the URL provided has been accessed or not?		
		driver.get("http://q.door.fund/");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("q.door.fund"));
		System.out.println("Application loaded");
	}
	
	@Test(dependsOnMethods="startapp")
	public void loginapp() {
		//Submitting the login credentials and checking whether the user has login the application or not?
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[1]/input")).sendKeys("farooq.u@hotmail.com");
		//((WebElement) driver.findElements(By.xpath("//*[@id=\"idSIButton9\"]"))).click();
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[2]/input")).sendKeys("ABC123ssi");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/button")).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		String status= driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/label")).getText().toString();	
		String estatus = "The email or password you entered is incorrect";
		if (status.equals(estatus)) {
			System.out.println("invalid login credentials");			
		}
		else {
			System.out.println("valid login credentials");
		}
	}
	@AfterClass
	public void closeapp() {
		driver.quit();
		System.out.println("Browser closed");
	}
	
}

