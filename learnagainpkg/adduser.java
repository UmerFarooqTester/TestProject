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
public class adduser {
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
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[1]/input")).sendKeys("amanda@doorfunds.com");
		//((WebElement) driver.findElements(By.xpath("//*[@id=\"idSIButton9\"]"))).click();
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/div[2]/input")).sendKeys("Liverpool1");
		
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/form/button")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		/*try {
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
		}*/
	}
	@Test(dependsOnMethods= "loginapp")
	public void registerpageopen() {
		System.out.println("Dashboard loaded");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[1]/div/div/li[1]/div/span")).click();
		
		System.out.println("Register page opened");		
	}
	
	@Test(dependsOnMethods= "registerpageopen")
	public void registeruser() {
		System.out.println("going to register a new user");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/ul/li/div/form/div[1]/input")).sendKeys("testeragent7@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/ul/li/div/form/div[2]/input")).sendKeys("Tester");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/ul/li/div/form/div[3]/input")).sendKeys("007");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/ul/li/div/form/div[4]/select")).sendKeys("Fund Investor");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/ul/li/div/form/div[6]/input")).sendKeys("1");
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/ul/li/div/form/div[7]/select")).sendKeys("False");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/ul/li/div/form/button")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String s = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[2]/div/ul/li/div/form/p")).getText();		
		if (s == "User registered!") {
			System.out.println("new user added");	
			}
		else {
			System.out.println("user already exists");	
		}
	}
	@AfterClass
	public void closeapp() {
		driver.quit();
		System.out.println("Browser closed");
	}
	
}

