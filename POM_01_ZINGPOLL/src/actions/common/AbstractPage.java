package actions.common;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
			sl.selectByIndex(Integer.parseInt(index +""));
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
		for(WebElement el : lsWebElements) {
			lsSizeOfElements.add(el.getSize());
		}
		return lsSizeOfElements;
	}
	
	public void checkTheCheckBox(By locator) {
		WebElement element = driver.findElement(locator);
		if(element.isSelected() == false) {
			element.click();
		}else {
			System.out.println("The checkbox has already checked");
		}
	}
	
	public void uncheckTheCheckBox(By locator) {
		WebElement element = driver.findElement(locator);
		if(element.isSelected() == true) {
			element.click();
		}else {
			System.out.println("The checkbox has already unchecked");
		}
	}
	
	public boolean isControlDisplayed(By locator) {
		if(driver.findElement(locator).isDisplayed() == true) {
			return true;
		}
		return false;
	}
	
	public boolean isControlSelected(By locator) {
		if(driver.findElement(locator).isSelected() == true) {
			return true;
		}
		return false;
	}
	
	public boolean isControlEnabled(By locator) {
		if(driver.findElement(locator).isEnabled() == true) {
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
	
	public void switchWindowById(String windowId) {
		driver.switchTo().window(windowId);
	}

}
