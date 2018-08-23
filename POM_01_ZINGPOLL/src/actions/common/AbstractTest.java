package actions.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AbstractTest extends AbstractPage{

	public AbstractTest(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	public WebDriver openBrowser(String browser) {
		switch (browser) {
		case Constant.CHROMEBROWSER:
			System.setProperty(Constant.CHORMEWEBDRIVER, Constant.CHROMEDRIVER);
			super.driver = new ChromeDriver();
			break;
			
		case Constant.FIREFOXBROWSER:
			super.driver = new FirefoxDriver();
			break;
			
		case Constant.IEBROWSER:
			System.setProperty(Constant.IEWEBDRIVER, Constant.IEDRIVER);
			super.driver = new ChromeDriver();
			break;

		default:
			System.out.println("The browser name is unidentifed");
			break;
		}
		super.driver.manage().window().maximize();
		super.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return super.driver;
	}

}
