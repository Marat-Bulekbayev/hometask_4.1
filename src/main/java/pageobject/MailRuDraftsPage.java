package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static util.Waiter.waitForElementToBeVisible;

public class MailRuDraftsPage extends AbstractPage {

    private static final String PAGE_URL = "https://e.mail.ru/drafts/";

    @FindBy(xpath = "//span[text()='Отправить']")
    private WebElement sendMailButton;

    @FindBy(xpath = "//span[@title='Закрыть']")
    private WebElement closeSentMailNotificationPopup;

    public MailRuDraftsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MailRuDraftsPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public MailRuDraftsPage sendDraftMail() {
        mailsContent.get(0).click();
        sendMailButton.click();
        closeSentMailNotificationPopup.click();
        return this;
    }

    public boolean isMailPresentInDrafts() {
        return isMailPresentInMailboxFolder(PAGE_URL);
    }

    public boolean isMailAbsentInDrafts() {
        waitForElementToBeVisible(driver, noUnfinishedOrUnsentMailsMessageLocator);
        return !mailsContent.isEmpty();
    }
}
