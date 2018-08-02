package tests;

import org.openqa.selenium.WebDriver;
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
		WebDriver driver = WebDriverSingleton.getWebDriverInstance();
		HomePage homePage = new HomePage(driver);
		homePage.startBrowser();
		AccountPage accountPage = homePage.login(MAILRU_LOGIN_FIRST_ACCOUNT, MAILRU_PASSWORD_FIRST_ACCOUNT);
		ScreenShooter.takeScreenshot();
		Assert.assertTrue(accountPage.isEmailPresentOnPage(EXPECTED_ACCOUNT_ICON), "User is not login!");
	}
// 	It's a good practice to move "start browser" actions into a separate method, e.g.
// 	@BeforeClass()
// 	public void startBrowser() {
// 	some actions
// 	}
	@Test(description = "Mail creation", dependsOnMethods = { "mailRuLoginTest" }, groups={"test_1"})
	public void mailRuMailCreationTest(){
		AccountPage accountPage = new AccountPage(driver);	
		
		CreateEmailPage mailCreationPage = accountPage.clickMailCreationBtn();
		mailCreationPage.fillMailAddress(MAIL_TO_ADDRESS);
		mailCreationPage.fillMailSubject(SUBJECT);
		mailCreationPage.fillMailBody(TEXT_BODY);
		mailCreationPage.clickSaveDraftBtn();
		
		DraftPage draftPage = accountPage.clickMailDraftMenuLink();
		String actualSubject = draftPage.getDraftMailSubject(0);
		Assert.assertEquals(actualSubject,SUBJECT);
		
		draftPage.openDraftMail(0);
// 		Please move 0 into constants
		String actualMailToAddress =  mailCreationPage.getMailToAddress();
		ScreenShooter.takeScreenshot();
		Assert.assertEquals(actualMailToAddress,MAIL_TO_ADDRESS + ",");	
		Assert.assertTrue(mailCreationPage.isMailBodyEnable(TEXT_BODY),"Required text body has not been found");
	}

}
