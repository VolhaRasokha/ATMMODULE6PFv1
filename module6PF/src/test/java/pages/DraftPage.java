package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DraftPage extends AccountPage {
	@FindBy(xpath = "//*[contains(@href,'https://e.mail.ru/compose/')]")
	private List<WebElement> draftMails;

	public CreateEmailPage getDraftMailBySubject(String subject) {
		WebElement draftMail = driver.findElement(By.partialLinkText(subject));
		waitForElementVisible(draftMail);
		highlightElement(draftMail);
		unHighlightElement(draftMail);
		draftMail.click();
		return new CreateEmailPage();
	}

	public String getDraftMailSubject(int index) {
		return getDraftMail(index).getAttribute("data-subject");
	}

	public CreateEmailPage openDraftMail(int index) {
		WebElement draftMail = getDraftMail(index);
		draftMail.click();
		return new CreateEmailPage();
	}
	
	private WebElement getDraftMail(int index) {
		WebElement draftMail = draftMails.get(index);
		return draftMail;
	}
}
