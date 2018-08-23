package testcase.login;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import actions.common.AbstractPage;
import actions.common.AbstractTest;

public class Login_01_LoginSuccessful {
	public AbstractTest abtractTest;
	public WebDriver driver;
	@BeforeMethod
	public void setUp() {
		abtractTest = new AbstractTest(AbstractPage.driver);
		driver = abtractTest.openBrowser("Chrome");
		
		
	}
	
	@Test
	public void test_01_loginsuccesful() {
		driver.get("https://www.google.com/search?q=love+vocab");
	}
	
	@AfterMethod
	public void teardown() {
		
	}
	

}
