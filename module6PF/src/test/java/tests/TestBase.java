package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;

import utils.WebDriverSingleton;
import pages.AccountPage;

public class TestBase {
	protected static final String MAILRU_LOGIN_FIRST_ACCOUNT = "vra_atmmodule5";
	protected static final String MAILRU_PASSWORD_FIRST_ACCOUNT = "123456789_Vra";
	protected static final String MAILRU_LOGIN_SECOND_ACCOUNT = "vra_atmmodule6";
	protected static final String MAILRU_PASSWORD_SECOND_ACCOUNT = "123456789_Vra";
	protected static final String MAILRU_URL = "https://mail.ru/";
	protected WebDriver driver;
	
	@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		AccountPage accountPage = PageFactory.initElements(WebDriverSingleton.getWebDriverInstance(), AccountPage.class);	
		accountPage.clickLogOut();
		WebDriverSingleton.kill();
	}

}
