import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Document;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestNGDemo {
	WebDriver driver = null;
	ExtentReports extent;
	ExtentHtmlReporter htmlReporter;
	ExtentTest test;



	@BeforeTest
	public void SetupTest() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\test-output\\myReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		String Projectpath = System.getProperty("user.dir");
		System.out.println("Project Path" + Projectpath);
		System.setProperty("webdriver.chrome.driver", Projectpath + "\\drivers\\geckodriver\\chromedriver_win32 (4)\\chromedriver.exe");
		driver = new ChromeDriver();	

	}

	@Test
	public void GetStartPage() throws Exception {
		extent.createTest("FirstTest", "Sample description");
		driver.get("https://q.passmate.co.uk/");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("q.passmate.co"));
		System.out.println("Application loaded");
		String Actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/section[1]/div/div[1]/div/h3")).getText();		
		String Expected = "Find your perfect driving instructor.";
		assertEquals(Actual, Expected);

		/*if (s == "Find your perfect driving instructor.") {
			System.out.println("Right page is opened.");	
			test.fail("Navigated to wrong page.");
		}
		else {
			System.out.println("Invalid page.");
			test.pass("Navigated to Home Page");
		}*/

		//test.log(Status.INFO, "This step shows usage of log(status, details)");
		//test.info("This step shows usage of info(details)");
		//test.addScreenCaptureFromPath("screenshot.png");


	}

	@Test(dependsOnMethods = "GetStartPage") 
	public void GetStarted() {
		driver.findElement(By.xpath("/html/body/div[1]/div/div/section[1]/div/div[1]/div/div/div")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		String currentURL2 = driver.getCurrentUrl();
		Assert.assertTrue(currentURL2.contains("uk/questions"));
		System.out.println("Questions Page loaded");	
	}

	@AfterMethod(alwaysRun = true)
	public void end(ITestResult result) throws IOException{
		test = extent.createTest("The Test is " + result.getName());

		if (result.getStatus() == ITestResult.FAILURE) {

			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
			test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report
		}
		else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, "TEST CASE SKIPPED IS " + result.getName());
		}
		else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "TEST CASE PASSED IS " + result.getName());
			//test.log(Status.PASS, "Entered the postcode, Selected the GENDER as Female, Selected the CARTYPE as Manual, ");
		}
	}

	//	@AfterMethod(alwaysRun = true)
	//	public void ExtentReportStatuses(ITestResult result) {
	//		
	//		if(result.getStatus()==ITestResult.FAILURE) {
	//		test.log(Status.FAIL, result.getThrowable());
	//		}
	//		else if(result.getStatus()==ITestResult.SUCCESS) {
	//			test.log(Status.PASS, "The test is passed." );
	//			}
	//		else if (result.getStatus()==ITestResult.SKIP) {
	//			test.log(Status.SKIP, "The test is Skipped." );
	//			}
	//		
	//	}

	@AfterTest
	public void CloseApp() {		
		driver.close();
		driver.quit();
		System.out.println("Browser closed");
		extent.flush();		
	}		
}