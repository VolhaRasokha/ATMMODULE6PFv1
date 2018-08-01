package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

	public AccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[@id='b-toolbar__left']//a[(@data-name = 'compose')]")
	private WebElement mailCreationBtn;

	@FindBy(xpath = "//*[contains(@class,'ico_folder_send')]")
	private WebElement mailSendMenuLink;

	@FindBy(xpath = "//*[contains(@class,'ico_folder_inbox')]")
	private WebElement mailIncomingMenuLink;

	@FindBy(xpath = "//*[@id='b-nav_folders']//i[contains(@class,'ico_folder_trash')]")
	private WebElement mailBasketMenuLink;

	@FindBy(id = "PH_logoutLink")
	private WebElement LogOutLink;

	@FindBy(xpath = "//*[contains(@class,'ico_folder_drafts')]")
	protected WebElement mailDraftMenuLink;

	public static By mailSentTitle = By
			.cssSelector("[class='message-sent__title']");

	public CreateEmailPage clickMailCreationBtn() {
		waitForElementVisible(mailCreationBtn);
		highlightElement(mailCreationBtn);
		mailCreationBtn.click();
		unHighlightElement(mailCreationBtn);
		return new CreateEmailPage(driver);
	}

	public DraftPage clickMailDraftMenuLink() {
		waitForElementVisible(mailDraftMenuLink);
		mailDraftMenuLink.click();
		return new DraftPage(driver);
	}

	public AccountPage clickMailSentMenuLink() {
		waitForElementVisible(mailSendMenuLink);
		highlightElement(mailSendMenuLink);
		unHighlightElement(mailSendMenuLink);
		mailSendMenuLink.click();
		return new AccountPage(driver);
	}

	public IncomingPage clickMailIncomingMenuLink() {
		waitForElementVisible(mailIncomingMenuLink);
		highlightElement(mailIncomingMenuLink);
		unHighlightElement(mailIncomingMenuLink);
		mailIncomingMenuLink.click();
		return new IncomingPage(driver);
	}

	public BasketPage clickBasketMenuLink() {
		waitForElementVisible(mailBasketMenuLink);
		highlightElement(mailBasketMenuLink);
		unHighlightElement(mailBasketMenuLink);
		mailBasketMenuLink.click();
		return new BasketPage(driver);
	}

	public HomePage clickLogOut() {
		waitForElementVisible(LogOutLink);
		highlightElement(LogOutLink);
		unHighlightElement(LogOutLink);
		LogOutLink.click();
		return new HomePage(driver);
	}

}
