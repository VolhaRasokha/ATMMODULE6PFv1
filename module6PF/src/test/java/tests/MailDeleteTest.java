package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ScreenShooter;

import pages.AccountPage;
import pages.BasketPage;
import pages.DraftPage;
import pages.HomePage;
import pages.IncomingPage;


public class MailDeleteTest extends TestBase {
	@Test (description = "Mail delete", dependsOnGroups = {"test_2"}, groups={"test_3"})
	public void mailRuMailDeleteTest() {
		
		HomePage homePage = new HomePage(driver);
		homePage.startBrowser();
		AccountPage accountPage = homePage.login(MAILRU_LOGIN_SECOND_ACCOUNT, MAILRU_PASSWORD_SECOND_ACCOUNT);
		
		IncomingPage incomingPage = accountPage.clickMailIncomingMenuLink();
		
		String subjectDeleteIncomingMail = incomingPage.getIncomingMailSubject(0);
		incomingPage.deleteIncomingMail(0);	
		ScreenShooter.takeScreenshot();
	    Assert.assertFalse(incomingPage.isEmailPresentOnPage(subjectDeleteIncomingMail));
	    
	    BasketPage basketPage = accountPage.clickBasketMenuLink();
	    
	    basketPage.refresh();
	    ScreenShooter.takeScreenshot();
	    Assert.assertTrue(basketPage.isEmailPresentOnPage(subjectDeleteIncomingMail));	
	}
	
	@Test(description = "Mail drag and drop from Basket to DRAFT", dependsOnGroups = {"test_2"}, dependsOnMethods = { "mailRuMailDeleteTest" }, groups={"test_3"})
	public void dragNDropMailTest(){
		AccountPage accountPage = new AccountPage(driver);
		BasketPage basketPage = accountPage.clickBasketMenuLink();
		
		String subject = basketPage.getDeleteMailSubject(0);
		basketPage.refresh();
		basketPage.dragNDropMailFromDraftToBasket(0);		

		basketPage.refresh();		
		DraftPage draftPage = accountPage.clickMailDraftMenuLink();
				
		ScreenShooter.takeScreenshot();
		Assert.assertTrue(draftPage.isEmailPresentOnPage(subject), "Email is not drag and drop to Basket");
	}
	


}
