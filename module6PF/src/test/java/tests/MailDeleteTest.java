package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ScreenShooter;
import utils.WebDriverSingleton;

import pages.AccountPage;
import pages.BasketPage;
import pages.DraftPage;
import pages.HomePage;
import pages.IncomingPage;


public class MailDeleteTest extends TestBase {
	@Test (description = "Mail delete", dependsOnGroups = {"test_2"}, groups={"test_3"})
	public void mailRuMailDeleteTest() {
		
		HomePage homePage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), HomePage.class);
		homePage.startBrowser();
		homePage.login(MAILRU_LOGIN_SECOND_ACCOUNT, MAILRU_PASSWORD_SECOND_ACCOUNT);
		
		AccountPage accountPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), AccountPage.class);
		accountPage.clickMailIncomingMenuLink();
		
		IncomingPage incomingPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), IncomingPage.class);

		String subjectDeleteIncomingMail = incomingPage.getIncomingMailSubject(0);
		incomingPage.deleteIncomingMail(0);	
		ScreenShooter.takeScreenshot();
	    Assert.assertFalse(incomingPage.isEmailPresentOnPage(subjectDeleteIncomingMail));
	    
	    accountPage.clickBasketMenuLink();
	    
	    BasketPage basketPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), BasketPage.class);

	    basketPage.refresh();
	    ScreenShooter.takeScreenshot();
	    Assert.assertTrue(basketPage.isEmailPresentOnPage(subjectDeleteIncomingMail));	
	}
	
	@Test(description = "Mail drag and drop from Basket to DRAFT", dependsOnGroups = {"test_2"}, dependsOnMethods = { "mailRuMailDeleteTest" }, groups={"test_3"})

	public void dragNDropMailTest(){
		AccountPage accountPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), AccountPage.class);
		accountPage.clickBasketMenuLink();
		
		BasketPage basketPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), BasketPage.class);
		String subject = basketPage.getDeleteMailSubject(0);
		basketPage.refresh();
		basketPage.dragNDropMailFromDraftToBasket(0);		

		basketPage.refresh();		
		accountPage.clickMailDraftMenuLink();
		
		DraftPage draftPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), DraftPage.class);
		
		ScreenShooter.takeScreenshot();
		Assert.assertTrue(draftPage.isEmailPresentOnPage(subject), "Email is not drag and drop to Basket");
	}
	


}
