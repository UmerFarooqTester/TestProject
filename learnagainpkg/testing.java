package learnagainpkg;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

public class testing {

	
	@Test(priority = 1)
	public void testlogin() {
		System.out.println("user logged in");
		Assert.assertEquals(12, 13);
		
	}
	
	@Test(priority = 2)
	public void selectitem() {
		System.out.println("item selected");
		
	}
	
	@Test(priority = 3)
	public void checkout() {
		System.out.println("checked out");
		
	}
	
}
