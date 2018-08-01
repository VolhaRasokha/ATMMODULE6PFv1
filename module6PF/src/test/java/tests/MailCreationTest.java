package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ScreenShooter;
import utils.WebDriverSingleton;

import pages.AccountPage;
import pages.CreateEmailPage;
import pages.DraftPage;
import pages.HomePage;

public class MailCreationTest extends TestBase {
	protected static final String MAILRU_LOGIN_FIRST_ACCOUNT = "vra_atmmodule5";
	protected static final String MAILRU_PASSWORD_FIRST_ACCOUNT = "123456789_Vra";
	private static final String EXPECTED_ACCOUNT_ICON = "vra_atmmodule5@mail.ru"; 
	public static final String MAIL_TO_ADDRESS = "vra_atmmodule6@mail.ru";
	private static long currentKey = System.currentTimeMillis();
	public static final String SUBJECT = "TestSubject" + currentKey;
	private static final String TEXT_BODY = "TestTextBody" + currentKey;
    
	
	@Test(description = "Login to mail.ru", groups={"test_1"})
	public void mailRuLoginTest() {
		
		HomePage homePage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), HomePage.class);
		homePage.startBrowser();
		homePage.login(MAILRU_LOGIN_FIRST_ACCOUNT, MAILRU_PASSWORD_FIRST_ACCOUNT);
		
		AccountPage accountPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), AccountPage.class);;
		ScreenShooter.takeScreenshot();
		Assert.assertTrue(accountPage.isEmailPresentOnPage(EXPECTED_ACCOUNT_ICON), "User is not login!");
	}
	
	@Test(description = "Mail creation", dependsOnMethods = { "mailRuLoginTest" }, groups={"test_1"})
	public void mailRuMailCreationTest(){
		AccountPage accountPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), AccountPage.class);	
		accountPage.clickMailCreationBtn();
		
		CreateEmailPage mailCreationPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), CreateEmailPage.class);	
		mailCreationPage.fillMailAddress(MAIL_TO_ADDRESS);
		mailCreationPage.fillMailSubject(SUBJECT);
		mailCreationPage.fillMailBody(TEXT_BODY);
		mailCreationPage.clickSaveDraftBtn();
		accountPage.clickMailDraftMenuLink();
		
		DraftPage draftPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), DraftPage.class);	
		String actualSubject = draftPage.getDraftMailSubject(0);
		Assert.assertEquals(actualSubject,SUBJECT);
		
		draftPage.openDraftMail(0);
		String actualMailToAddress =  mailCreationPage.getMailToAddress();
		ScreenShooter.takeScreenshot();
		Assert.assertEquals(actualMailToAddress,MAIL_TO_ADDRESS + ",");	
		Assert.assertTrue(mailCreationPage.isMailBodyEnable(TEXT_BODY),"Required text body has not been found");
	}

}
