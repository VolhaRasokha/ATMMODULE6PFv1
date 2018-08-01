package pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class IncomingPage extends BasePage {

	public IncomingPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//*[contains(@href,'https://e.mail.ru/thread/')]")
	private List<WebElement> incomingMails;

	@FindBy(xpath = "//div[@class='b-datalist__item__body']//*[@class='b-checkbox__box']")
	private List<WebElement> checkBoxIncomingMails;

	@FindBy(xpath = "//*[@id='b-toolbar__right']//div[@data-name='remove']")
	private WebElement deleteBtn;

	public String getIncomingMailSubject(int index) {
		return incomingMails.get(index).getAttribute("data-subject");
	}

	public IncomingPage deleteIncomingMail(int index) {
		WebElement firstIncomingMailCheckBox = checkBoxIncomingMails.get(index);
		firstIncomingMailCheckBox.click();
		deleteBtn.click();
		return new IncomingPage(driver);
	}

	// Delete mail via context menu and Javascript Executor
	public IncomingPage deleteMailByActionsJS(int index) {
		WebElement mail = incomingMails.get(index);

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].style.backgroundColor = 'yellow'", mail);
		new Actions(driver).contextClick(mail).build().perform();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();",
				deleteBtn);
		waitForElementVisible(mail);
		return new IncomingPage(driver);
	}

	public IncomingPage sendMailtoArchiveByJS(int index) {
		WebElement mail = incomingMails.get(index);

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].style.backgroundColor = 'yellow'", mail);
		new Actions(driver)
				.contextClick(mail)
				.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN,
						Keys.ENTER).build().perform();

		return new IncomingPage(driver);
	}

}
