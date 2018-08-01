package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	private static final String MAILRU_URL = "https://mail.ru/";
	
	@FindBy(id = "mailbox:login")
	private WebElement loginField;

	@FindBy(id = "mailbox:password")
	private WebElement passwordField;
	
	@FindBy(id = "mailbox:submit")
	private WebElement submitBtn;
	
	public HomePage startBrowser(){
		driver.get(MAILRU_URL);
		return new HomePage();
	}
	
	public AccountPage login(String login, String password){
		waitForElementVisible(loginField);
		highlightElement(loginField);
		loginField.clear();
		loginField.sendKeys(login);
		unHighlightElement(loginField);
		
		waitForElementVisible(passwordField);
		highlightElement(passwordField);
		passwordField.clear();
		passwordField.sendKeys(password);
		unHighlightElement(passwordField);
		
		waitForElementVisible(submitBtn);
		highlightElement(submitBtn);
		unHighlightElement(submitBtn);
		submitBtn.click();
		
		new Actions(driver).click(submitBtn).build().perform();
		return new AccountPage();
	}
	
}
