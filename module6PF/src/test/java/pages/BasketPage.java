package pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends AccountPage {
	
	@FindBy(xpath = "//a[contains(@href,'https://e.mail.ru/thread/')]")
	private List <WebElement> mailsInBasket;
	
	public BasketPage dragNDropMailFromDraftToBasket(int index){
		WebElement element = mailsInBasket.get(index);
		highlightElement(element);
		WebElement target = mailDraftMenuLink;
		highlightElement(mailDraftMenuLink);
		unHighlightElement(mailDraftMenuLink);
		unHighlightElement(element);
		new Actions(driver).dragAndDrop(element,target).build().perform();
		System.out.println("DragAndDrop was successeful!");
		return this;
	}
	
	public String getDeleteMailSubject(int index) {
		WebElement deleteMail = mailsInBasket.get(index);
		return deleteMail.getAttribute("data-subject");
	}
}
