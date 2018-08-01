package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ScreenShooter;
import utils.WebDriverSingleton;

import pages.AccountPage;
import pages.CreateEmailPage;
import pages.DraftPage;
import pages.HomePage;
import pages.IncomingPage;

public class MailSendTest extends TestBase{
	private By SEARCH_MAIL_SENT_TITLE_LOCATOR = By.cssSelector("[class='message-sent__title']");
	
	@Test(description = "Mail sending", dependsOnGroups = {"test_1"}, groups={"test_2"})
	public void mailRuMailSendingTest() {
		
		HomePage homePage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), HomePage.class);
		homePage.startBrowser();
		homePage.login(MAILRU_LOGIN_FIRST_ACCOUNT, MAILRU_PASSWORD_FIRST_ACCOUNT);
		
		AccountPage accountPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), AccountPage.class);
		accountPage.clickMailDraftMenuLink();
		
		DraftPage draftPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), DraftPage.class);
		
		String actualSubject = draftPage.getDraftMailSubject(0);
		draftPage.getDraftMailBySubject(actualSubject);
		
		CreateEmailPage mailCreationPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), CreateEmailPage.class);
		mailCreationPage.clickMailSendBtn();
		
		accountPage.isElementPresent(SEARCH_MAIL_SENT_TITLE_LOCATOR);
		
		accountPage.refresh();
		accountPage.clickMailDraftMenuLink();
		
		ScreenShooter.takeScreenshot();
		Assert.assertFalse(draftPage.isEmailPresentOnPage(actualSubject), "Email exists in Draft folder");
		
		accountPage.clickMailSentMenuLink();
		
		Assert.assertTrue(accountPage.isEmailPresentOnPage(actualSubject), "Email does not exist in SENT folder");
		
		accountPage.clickLogOut();
		
		homePage.login(MAILRU_LOGIN_SECOND_ACCOUNT, MAILRU_PASSWORD_SECOND_ACCOUNT);
		accountPage.refresh();
		accountPage.clickMailIncomingMenuLink();
		
		IncomingPage incomingPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), IncomingPage.class);

		Assert.assertTrue(incomingPage.isEmailPresentOnPage(actualSubject),"Email does not exist in Incoming folder");
	}

}
