package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ScreenShooter;

import pages.AccountPage;
import pages.CreateEmailPage;
import pages.DraftPage;
import pages.HomePage;
import pages.IncomingPage;

public class MailSendTest extends TestBase {

	@Test(description = "Mail sending", dependsOnGroups = { "test_1" }, groups = { "test_2" })
	public void mailRuMailSendingTest() {

		HomePage homePage = new HomePage(driver);
		homePage.startBrowser();
		AccountPage accountPage = homePage.login(MAILRU_LOGIN_FIRST_ACCOUNT,
				MAILRU_PASSWORD_FIRST_ACCOUNT);

		DraftPage draftPage = accountPage.clickMailDraftMenuLink();

		String actualSubject = draftPage.getDraftMailSubject(0);
		CreateEmailPage mailCreationPage = draftPage
				.getDraftMailBySubject(actualSubject);

		mailCreationPage.clickMailSendBtn();

		accountPage.isElementPresent(AccountPage.mailSentTitle);

		accountPage.refresh();
		accountPage.clickMailDraftMenuLink();

		ScreenShooter.takeScreenshot();
		Assert.assertFalse(draftPage.isEmailPresentOnPage(actualSubject),
				"Email exists in Draft folder");

		accountPage.clickMailSentMenuLink();

		Assert.assertTrue(accountPage.isEmailPresentOnPage(actualSubject),
				"Email does not exist in SENT folder");

		accountPage.clickLogOut();

		homePage.login(MAILRU_LOGIN_SECOND_ACCOUNT,
				MAILRU_PASSWORD_SECOND_ACCOUNT);
		accountPage.refresh();
		IncomingPage incomingPage = accountPage.clickMailIncomingMenuLink();

		Assert.assertTrue(incomingPage.isEmailPresentOnPage(actualSubject),
				"Email does not exist in Incoming folder");
	}

}
