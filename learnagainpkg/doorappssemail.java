package learnagainpkg;
import java.io.File;
//import java.net.PasswordAuthentication;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//for mail these libs are used
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
//import org.eclipse.jetty.websocket.api.Session;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
//import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeBodyPart;

//import sun.rmi.transport.Transport;
public class doorappssemail {
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
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		// Here will compare if test is failing then only it will enter into if condition
		if(ITestResult.FAILURE==result.getStatus())
		{
			try 
			{
				// Create reference of TakesScreenshot
				TakesScreenshot ts=(TakesScreenshot)driver;

				// Call method to capture screenshot
				File source=ts.getScreenshotAs(OutputType.FILE);

				// Copy files to specific location here it will save all screenshot in our project home directory and
				// result.getName() will return name of test case so that screenshot name will be same
				FileUtils.copyFile(source, new File("./ss/"+result.getName()+".png"));
				//logger.log(LogStatus.FAIL,"Failed"+result.getName());
				System.out.println("failed Screenshot taken");
			} 
			catch (Exception e)
			{

				System.out.println("Exception while taking screenshot "+e.getMessage());
			} 
		}
	}
	
	@AfterSuite
	public void emailreport()
	{
		Properties props = new Properties();
		 
		// this will set host of server- you can change based on your requirement 
		props.put("mail.smtp.host", "smtp.gmail.com");
 
		// set the port of socket factory 
		props.put("mail.smtp.socketFactory.port", "465");
 
		// set socket factory
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
 
		// set the authentication to true
		props.put("mail.smtp.auth", "true");
 
		// set the port of SMTP server
		props.put("mail.smtp.port", "465");
 
		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props,
 
				new javax.mail.Authenticator() {
 
					protected PasswordAuthentication getPasswordAuthentication() {
 
					return new PasswordAuthentication("testerfarooq1@gmail.com", "Mentorpk1");
 
					} 
				});
		try {
			 
			// Create object of MimeMessage class
			Message message = new MimeMessage(session);
 
			// Set the from address
			message.setFrom(new InternetAddress("testerfarooq1@gmail.com"));
 
			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("testerfarooq1@gmail.com"));
            
                        // Add the subject link
			message.setSubject("Emailed Test Report");
 
			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();
 
			// Set the body of email
			messageBodyPart1.setText("Hey Farooq, Attached is a test Report and is mailed to you by tester007.");
 
			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
 
			// Mention the file which you want to send
			String filename = "E:\\Eclipse\\workspace\\learnagain\\test-output\\emailable-report.html";
 
			// Create data source and pass the filename
			DataSource source = new FileDataSource(filename);
 
			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));
 
			// set the file
			messageBodyPart2.setFileName(filename);
 
			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();
 
			// add body part 1
			multipart.addBodyPart(messageBodyPart2);
 
			// add body part 2
			multipart.addBodyPart(messageBodyPart1);
 
			// set the content
			message.setContent(multipart);
 
			// finally send the email
			Transport.send(message);
 
			System.out.println("=====Email Sent=====");
 
		} 
		catch (MessagingException e) {
 
			throw new RuntimeException(e);
		}	
	}	
}

