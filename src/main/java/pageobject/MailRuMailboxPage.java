package pageobject;

import businessobject.Email;
import enums.MailboxFolder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import static util.Waiter.*;

public class MailRuMailboxPage extends AbstractPage{

    private static final String PAGE_URL = "https://e.mail.ru/inbox";
    private static final String DRAFT_FOLDER_URL = "https://e.mail.ru/drafts/";
    private static final String SENT_FOLDER_URL = "https://e.mail.ru/sent/";

    @FindBy(xpath = "//a[@title='Написать письмо']")
    WebElement writeNewMail;

    @FindBy(xpath = "//div[@data-name='to']/div/div/div/label")
    WebElement mailTo;

    @FindBy(xpath = "//input[@name='Subject']")
    WebElement mailSubject;

    @FindBy(xpath = "//div[@dir='true']/div[1]")
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

    @FindBy(xpath = "//div[@data-testid='whiteline-account']")
    WebElement userManager;

    @FindBy(xpath = "//div[text()='Выйти']")
    WebElement logOut;

    private final By noUnfinishedOrUnsentMailsMessageLocator = By.xpath("//span[@class='octopus__title']");

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

    public MailRuMailboxPage writeNewDraftMail(Email email) {
        writeNewMail.click();
        mailTo.sendKeys(email.getAddressee());
        mailSubject.sendKeys(email.getEmailSubject());
        mailBody.sendKeys(email.getEmailText());
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

    public boolean isMailContentVerified(Email email) {
        return mailsContent.get(0).getText().contains(email.getAddressee()) &&
               mailsContent.get(1).getText().contains(email.getEmailSubject()) &&
               mailsContent.get(2).getText().contains(email.getEmailText());
    }

    public boolean isMailPresentInMailboxFolder(MailboxFolder folder) {
        switch (folder) {
            case DRAFT :
                waitForRightURL(driver, DRAFT_FOLDER_URL);
                return !mailsContent.isEmpty();
            case SENT :
                waitForRightURL(driver, SENT_FOLDER_URL);
                return !mailsContent.isEmpty();
            default :
                return false;
        }
    }

    public boolean isMailAbsentInMailboxFolder() {
        waitForElementToBeVisible(driver, noUnfinishedOrUnsentMailsMessageLocator);
        return !mailsContent.isEmpty();
    }
}
