package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.WebDriverSingleton;

public class BasePage {
	private static final int DEFAULT_TIMEOUT = 15;
	
	protected WebDriver driver;

	public BasePage() {
		this.driver = WebDriverSingleton.getWebDriverInstance();
}
	
	protected void waitForElementVisible(WebElement elements) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElements(elements));
    }
	
	public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

	public boolean isEmailPresentOnPage(String by_subject) {
		return isElementPresent(By.xpath("//*[text()='" + by_subject + "']"));
	}
	
	protected void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = 'yellow'", element);
    }

    protected void unHighlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = ''", element);
    }
      
    public void refresh(){
    	driver.navigate().refresh();
    }

}
