package pageobject;

import businessobject.Email;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailRuInboxPage extends AbstractPage{

    private static final String PAGE_URL = "https://e.mail.ru/inbox";

    @FindBy(xpath = "//div[@data-name='to']/div/div/div/label")
    private WebElement mailTo;

    @FindBy(xpath = "//input[@name='Subject']")
    private WebElement mailSubject;

    @FindBy(xpath = "//div[@dir='true']/div[1]")
    private WebElement mailBody;

    @FindBy(xpath = "//span[text()='Сохранить']")
    private WebElement saveMailToDraftButton;

    @FindBy(xpath = "//button[@title='Закрыть']")
    private WebElement closeNewMailPopUp;

    public MailRuInboxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MailRuInboxPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public MailRuInboxPage writeNewDraftMail(Email email) {
        writeNewMail(mailTo, mailSubject, mailBody, email);
        saveMailToDraftButton.click();
        closeNewMailPopUp.click();
        return this;
    }
}
