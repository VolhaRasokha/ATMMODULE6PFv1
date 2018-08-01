package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

	
	@FindBy(xpath = "//*[@id='b-toolbar__left']//a[(@data-name = 'compose')]")
	private WebElement mailCreationBtn;

	@FindBy(xpath = "//*[contains(@class,'ico_folder_drafts')]")
	protected WebElement mailDraftMenuLink;
	
	@FindBy(xpath = "//*[contains(@class,'ico_folder_send')]")
	private WebElement mailSendMenuLink;
	
	@FindBy(xpath = "//*[contains(@class,'ico_folder_inbox')]")
	private WebElement mailIncomingMenuLink;
	
	@FindBy(xpath = "//*[@id='b-nav_folders']//i[contains(@class,'ico_folder_trash')]")
	private WebElement mailBasketMenuLink;
	
	@FindBy(id = "PH_logoutLink")
	private WebElement LogOutLink;
	
	public CreateEmailPage clickMailCreationBtn(){
		waitForElementVisible(mailCreationBtn);
		highlightElement(mailCreationBtn);
		mailCreationBtn.click();
		unHighlightElement(mailCreationBtn);
		return new CreateEmailPage();
	}
	
	public DraftPage clickMailDraftMenuLink(){
		waitForElementVisible(mailDraftMenuLink);
		mailDraftMenuLink.click();
		return new DraftPage();
	}
	
	public AccountPage clickMailSentMenuLink(){
		waitForElementVisible(mailSendMenuLink);
		highlightElement(mailSendMenuLink);
		unHighlightElement(mailSendMenuLink);
		mailSendMenuLink.click();
		return new AccountPage();
	}
	
	public IncomingPage clickMailIncomingMenuLink(){
		waitForElementVisible(mailIncomingMenuLink);
		highlightElement(mailIncomingMenuLink);
		unHighlightElement(mailIncomingMenuLink);
		mailIncomingMenuLink.click();
		return new IncomingPage();
	}
	
	public BasketPage clickBasketMenuLink(){
		waitForElementVisible(mailBasketMenuLink);
		highlightElement(mailBasketMenuLink);
		unHighlightElement(mailBasketMenuLink);
		mailBasketMenuLink.click();
		return new BasketPage();
	}
	
	public HomePage clickLogOut(){
		waitForElementVisible(LogOutLink);
		highlightElement(LogOutLink);
		unHighlightElement(LogOutLink);
		LogOutLink.click();
		return new HomePage();
	}

}
