package pageobject;

import enums.MailboxFolder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import static util.Waiter.*;

public class MailRuMailboxPage extends AbstractPage{

    private static final String PAGE_URL = "https://e.mail.ru/inbox";

    @FindBy(xpath = "//a[@title='Написать письмо']")
    WebElement writeNewMail;

    @FindBy(xpath = "//div[@data-name='to']/div/div/div/label")
    WebElement mailTo;

    @FindBy(xpath = "//input[@name='Subject']")
    WebElement mailSubject;

    @FindBy(xpath = "//div[@dir='true']/div")
    WebElement mailBody;

    @FindBy(xpath = "//span[text()='Сохранить']")
    WebElement saveMailToDraftButton;

    @FindBy(xpath = "//span[text()='Отправить']")
    WebElement sendMailButton;

    @FindBy(xpath = "//button[@title='Закрыть']")
    WebElement closeNewMailPopUp;

    @FindBy(xpath = "//span[@title='Закрыть']")
    WebElement closeSentMailNotificationPopup;

    @FindBy(xpath = "//div[text()='Входящие']")
    WebElement mailInboxFolder;

    @FindBy(xpath = "//div[text()='Черновики']")
    WebElement mailDraftsFolder;

    @FindBy(xpath = "//div[text()='Отправленные']")
    WebElement mailSentFolder;

    @FindBy(xpath = "//div[@class='llc__content']/div/span")
    List<WebElement> mailsContent;

    @FindBy(xpath = "//div[@aria-label='auto.test94@mail.ru']")
    WebElement userManager;

    @FindBy(xpath = "//div[text()='Выйти']")
    WebElement logOut;

    public MailRuMailboxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MailRuMailboxPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public boolean isUserLoggedInMailbox() {
        return writeNewMail.isDisplayed();
    }

    public void logOutFromMailbox() {
        userManager.click();
        logOut.click();
    }

    public MailRuMailboxPage writeNewDraftMail(String addressee, String mailTheme, String mailText) {
        writeNewMail.click();
        mailTo.sendKeys(addressee);
        mailSubject.sendKeys(mailTheme);
        mailBody.sendKeys(mailText);
        saveMailToDraftButton.click();
        closeNewMailPopUp.click();
        return this;
    }

    public MailRuMailboxPage openMailboxFolder(MailboxFolder folder) {
        switch (folder) {
            case DRAFT:
                mailDraftsFolder.click();
                return this;
            case SENT:
                mailSentFolder.click();
                return this;
            default:
                mailInboxFolder.click();
                return this;
        }
    }

    public MailRuMailboxPage sendDraftMail() {
        mailsContent.get(0).click();
        sendMailButton.click();
        closeSentMailNotificationPopup.click();
        return this;
    }

    public boolean isMailContentVerified(String mailAddressee, String mailSubject, String mailText) {
        return mailsContent.get(0).getText().contains(mailAddressee) &&
               mailsContent.get(1).getText().contains(mailSubject) &&
               mailsContent.get(2).getText().contains(mailText);
    }

    public boolean isMailPresentInMailboxFolder() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return !mailsContent.isEmpty();
    }
}
