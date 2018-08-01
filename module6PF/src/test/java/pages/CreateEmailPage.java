package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class CreateEmailPage extends BasePage{
	
	@FindBy(css = "textarea[data-original-name = 'To']")
	private WebElement mailToAddress;
	
	@FindBy(name = "Subject")
	private WebElement mailSubject;

	@FindBy(xpath = "//iframe[starts-with(@id,'toolkit')]")
	private WebElement mailBody;
	
	@FindBy(id = "tinymce")
	private WebElement textBody;
	
	@FindBy(xpath = "//*[@id='b-toolbar__right']//*[@data-name='saveDraft']")
	private WebElement saveAsDraftBtn;
	
	@FindBy(xpath = "//*[@id='b-toolbar__right']//*[@data-name='send']")
	private WebElement sendBtn;
	
	@FindBy(xpath = "//*[@id='b-toolbar__right']//*[@data-mnemo='saveStatus']")
	private WebElement saveStatus;
	
	@FindBy(xpath = "//*[@id='compose_to']")
	private WebElement actualMailToAddress;
	
	
	public CreateEmailPage fillMailAddress(String mailAddress){
		waitForElementVisible(mailToAddress);
		highlightElement(mailToAddress);
		mailToAddress.clear();
		mailToAddress.sendKeys(mailAddress);
		unHighlightElement(mailToAddress);
		return new CreateEmailPage();
	}
	
	public CreateEmailPage fillMailSubject(String subject){	
		waitForElementVisible(mailSubject);
		highlightElement(mailSubject);
		mailSubject.clear();
		mailSubject.sendKeys(subject);
		unHighlightElement(mailSubject);
		return new CreateEmailPage();
	}
	
	public CreateEmailPage fillMailBody(String body){
		driver.switchTo().frame(mailBody);
		textBody.clear();
		textBody.sendKeys(body);
		driver.switchTo().defaultContent();	
		return new CreateEmailPage();
	}
	
	public CreateEmailPage clickSaveDraftBtn(){
		highlightElement(saveAsDraftBtn);
		saveAsDraftBtn.click();
		unHighlightElement(saveAsDraftBtn);
		waitForElementVisible(saveStatus);
		highlightElement(saveStatus);
		unHighlightElement(saveStatus);
		return new CreateEmailPage(); 
	}

	
	public AccountPage clickMailSendBtn(){
		waitForElementVisible(mailToAddress);
		sendBtn.click();
		return new AccountPage();
	}
	
	public String getMailToAddress(){
		return actualMailToAddress.getAttribute("value");
	}
	
	public boolean isMailBodyEnable(String textBody){
		By SEARCH_ACTUAL_MAIL_BODY_LOCATOR = By.xpath("//*[text()= '" + textBody + "']");
		return driver.findElement(SEARCH_ACTUAL_MAIL_BODY_LOCATOR).isEnabled();
	}
	
	public CreateEmailPage fillMailByJS(String valueAddress){
		new Actions(driver).sendKeys(mailToAddress, valueAddress).build().perform();
		new Actions(driver).sendKeys(mailSubject, "test"+ System.nanoTime()).build().perform();
		
		driver.switchTo().frame(mailBody);
		new Actions(driver).sendKeys(textBody, "to test actions").build().perform();
		driver.switchTo().defaultContent();		
		return new CreateEmailPage();
	}
	
}
