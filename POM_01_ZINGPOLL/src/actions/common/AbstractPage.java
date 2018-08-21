package actions.common;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class AbstractPage {
	protected WebDriver driver;

	public AbstractPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToElement(By locator) {
		driver.findElement(locator).click();
	}

	public void sendkeyToElement(By locator, String value) {
		driver.findElement(locator).sendKeys(value);
	}

	public void selectItemInDropdownByIndex(By locator, int index) {
		try {
			Select sl = new Select(driver.findElement(locator));
			sl.selectByIndex(Integer.parseInt(index + ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectItemInDropdownByValue(By locator, String value) {
		try {
			Select sl = new Select(driver.findElement(locator));
			sl.selectByValue(value.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectItemInDropdownByVisibleText(By locator, String text) {
		try {
			Select sl = new Select(driver.findElement(locator));
			sl.selectByVisibleText(text.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public WebElement getFirstItemSelected(By locator) {
		Select sl = new Select(driver.findElement(locator));
		return sl.getFirstSelectedOption();
	}

	public String getAtributeValue(By locator, String attribute) {
		return driver.findElement(locator).getAttribute(attribute.toString());
	}

	public String getTextElement(By locator) {
		return driver.findElement(locator).getText();

	}

	public Dimension getSizeElement(By locator) {
		return driver.findElement(locator).getSize();
	}

	public List<Dimension> getSizeElements(By locator) {
		List<Dimension> lsSizeOfElements = new ArrayList<>();
		List<WebElement> lsWebElements = driver.findElements(locator);
		for (WebElement el : lsWebElements) {
			lsSizeOfElements.add(el.getSize());
		}
		return lsSizeOfElements;
	}

	public void checkTheCheckBox(By locator) {
		WebElement element = driver.findElement(locator);
		if (element.isSelected() == false) {
			element.click();
		} else {
			System.out.println("The checkbox has already checked");
		}
	}

	public void uncheckTheCheckBox(By locator) {
		WebElement element = driver.findElement(locator);
		if (element.isSelected() == true) {
			element.click();
		} else {
			System.out.println("The checkbox has already unchecked");
		}
	}

	public boolean isControlDisplayed(By locator) {
		if (driver.findElement(locator).isDisplayed() == true) {
			return true;
		}
		return false;
	}

	public boolean isControlSelected(By locator) {
		if (driver.findElement(locator).isSelected() == true) {
			return true;
		}
		return false;
	}

	public boolean isControlEnabled(By locator) {
		if (driver.findElement(locator).isEnabled() == true) {
			return true;
		}
		return false;
	}

	public void acceptAlert(Alert alert) {
		alert.accept();
	}

	public void cancelAlert(Alert alert) {
		alert.dismiss();
	}

	public String getTextAlert(Alert alert) {
		return alert.getText();
	}

	public void sendkeyToAlert(Alert alert, String value) {
		alert.sendKeys(value);
	}

	// can viet lai.
	public void switchWindowById(String windowId) {
		driver.switchTo().window(windowId);

	}

	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWindowWithoutParent(String parentWindow) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void switchToIframe(By framelocator) {
		driver.switchTo().frame(driver.findElement(framelocator));
	}

	public void doubleClickToElement(Actions action, By locator) {
		action.doubleClick(driver.findElement(locator));
	}

	public void hoverMouseToElement(Actions action, By locator) {
		action.moveToElement(driver.findElement(locator)).perform();
	}

	public void rightClickToElement(Actions action, By locator) {
		action.contextClick(driver.findElement(locator)).perform();
	}

	public void dragAndDrop(Actions action, By fromLocator, By toLocator) {
		WebElement fromElement = driver.findElement(fromLocator);
		WebElement toElement = driver.findElement(toLocator);
		action.dragAndDrop(fromElement, toElement).perform();
	}

	public void uploadBySendkey(By locator, String key) {
		driver.findElement(locator).sendKeys(key);
	}

	public void uploadByAutoIT(By locator, String browserName, String filePath) throws IOException {
		driver.findElement((locator)).click();
		Runtime.getRuntime().exec(new String[] { setUpBrowserLibForAutoIT(browserName), filePath });
	}

	public String setUpBrowserLibForAutoIT(String browser) {
		switch (browser) {
		case Constant.CHROMEBROWSER:
			return ".\\libs\\chrome.exe";

		case Constant.FIREFOXBROWSER:
			return ".\\libs\\firefox.exe";

		case Constant.IEBROWSER:
			return ".\\libs\\ie.exe";

		default:
			System.out.println("The browser name not found");
			return null;
		}
	}

	public void uploadByRobotClass(String filePath, By locator) throws InterruptedException, AWTException {
		StringSelection fileSelect = new StringSelection(filePath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(fileSelect, null);
		driver.findElement(locator).click();

		Robot robot = new Robot();
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		Thread.sleep(1000);

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		Thread.sleep(1000);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
	}
	
	public void executeJavascriptToBrowser() {
		
	}
	
	
	

}
