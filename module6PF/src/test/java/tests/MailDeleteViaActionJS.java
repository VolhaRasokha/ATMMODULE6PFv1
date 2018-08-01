package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.AccountPage;
import pages.CreateEmailPage;
import pages.HomePage;
import pages.IncomingPage;

public class MailDeleteViaActionJS extends TestBase{
	
	@Test(description = "To test actions")
	public void actionsTest(){
		String mailAddress = "vra_atmmodule6@mail.ru";
		
		HomePage homePage = new HomePage(driver);
		homePage.startBrowser();
		
		AccountPage accountPage = homePage.login(MAILRU_LOGIN_FIRST_ACCOUNT, MAILRU_PASSWORD_FIRST_ACCOUNT);

		CreateEmailPage mailCreationPage = accountPage.clickMailCreationBtn();
		mailCreationPage.fillMailByJS(mailAddress);
		mailCreationPage.clickMailSendBtn();
		
	    accountPage.clickMailCreationBtn();
		mailCreationPage.fillMailByJS(mailAddress);
		mailCreationPage.clickMailSendBtn();
		
		
		accountPage.clickLogOut();
		
		homePage.login(MAILRU_LOGIN_SECOND_ACCOUNT, MAILRU_PASSWORD_SECOND_ACCOUNT);
		
		IncomingPage incomingPage = new IncomingPage(driver);
		String subjectOfFirstEmail = incomingPage.getIncomingMailSubject(0);
		String subjectOfSecondEmail = incomingPage.getIncomingMailSubject(1);

		incomingPage.deleteMailByActionsJS(0);		
		incomingPage.refresh();
		String actualSubjectOfFirstEmail = incomingPage.getIncomingMailSubject(0);
		Assert.assertNotEquals(subjectOfFirstEmail, actualSubjectOfFirstEmail);

		incomingPage.sendMailtoArchiveByJS(0);
		incomingPage.refresh();
		String actualSubjectOfSecondEmail = incomingPage.getIncomingMailSubject(0);
		Assert.assertNotEquals(subjectOfSecondEmail, actualSubjectOfSecondEmail);
	}
	

}
