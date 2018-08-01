package pages;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class IncomingPage extends BasePage {
	
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
		return new IncomingPage();
	}
	
	//Delete mail via context menu and Javascript Executor
	public IncomingPage deleteMailsByActionsJS(int index0, int index1){
		WebElement mail_0 = incomingMails.get(index0);
		WebElement mail_1 = incomingMails.get(index1);
		((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = 'yellow'", mail_0);
		 new Actions(driver).contextClick(mail_0).sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER).build().perform();
		 
		((JavascriptExecutor) driver).executeScript("arguments[0].style.backgroundColor = 'yellow'", mail_1);
		
		 new Actions(driver).contextClick(mail_1).build().perform();
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);

		return new IncomingPage();
	}

}
