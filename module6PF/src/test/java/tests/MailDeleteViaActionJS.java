package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import utils.WebDriverSingleton;

import pages.AccountPage;
import pages.CreateEmailPage;
import pages.HomePage;
import pages.IncomingPage;

public class MailDeleteViaActionJS extends TestBase{
	
	@Test(description = "To test actions")
	public void actionsTest(){
		String mailAddress = "vra_atmmodule6@mail.ru";
		
		HomePage homePage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), HomePage.class);
		homePage.startBrowser();
		homePage.login(MAILRU_LOGIN_FIRST_ACCOUNT, MAILRU_PASSWORD_FIRST_ACCOUNT);
		
		AccountPage accountPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), AccountPage.class);
		accountPage.clickMailCreationBtn();
		
		CreateEmailPage mailCreationPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), CreateEmailPage.class);
		mailCreationPage.fillMailByJS(mailAddress);
		mailCreationPage.clickMailSendBtn();
		
	    accountPage.clickMailCreationBtn();
		mailCreationPage.fillMailByJS(mailAddress);
		mailCreationPage.clickMailSendBtn();
		
		
		accountPage.clickLogOut();
		
		homePage.login(MAILRU_LOGIN_SECOND_ACCOUNT, MAILRU_PASSWORD_SECOND_ACCOUNT);
		
		IncomingPage incomingPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), IncomingPage.class);
		String subjectOfFirstEmail = incomingPage.getIncomingMailSubject(0);
		String subjectOfSecondEmail = incomingPage.getIncomingMailSubject(1);

		incomingPage.deleteMailsByActionsJS(0,1);
		
		incomingPage.refresh();

		Assert.assertFalse(incomingPage.isEmailPresentOnPage(subjectOfFirstEmail), "Email is present in Incoming folder");
		Assert.assertFalse(incomingPage.isEmailPresentOnPage(subjectOfSecondEmail), "Email is present in Incoming folder");


	}
	

}
