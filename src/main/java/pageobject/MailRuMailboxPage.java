package pageobject;

import enums.MailboxFolderEnum;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

import static util.Waiter.*;

public class MailRuMailboxPage {

    private static final String DRAFT_FOLDER_URL = "https://e.mail.ru/drafts/";
    private static final String SENT_FOLDER_URL = "https://e.mail.ru/sent/";

    private WebDriver driver;

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

    @FindBy(xpath = "//div[text()='Черновики']")
    WebElement mailDrafts;

    @FindBy(xpath = "//div[text()='Отправленные']")
    WebElement sentMails;

    @FindBy(xpath = "//div[@class='llc__content']/div/span")
    List<WebElement> mailsContent;

    @FindBy(xpath = "//div[@aria-label='auto.test94@mail.ru']")
    WebElement userManager;

    @FindBy(xpath = "//div[text()='Выйти']")
    WebElement logOut;

    public MailRuMailboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isUserLoggedInMailbox() {
        waitForElementToBeClickable(driver, writeNewMail);
        return writeNewMail.isDisplayed();
    }

    public void logOutFromMailbox() {
        waitForElementToBeClickable(driver, userManager);
        userManager.click();
        waitForElementToBeClickable(driver, logOut);
        logOut.click();
    }

    public MailRuMailboxPage writeNewMail(String addressee, String mailTheme, String mailText) {
        waitForElementToBeClickable(driver, writeNewMail);
        writeNewMail.click();
        waitForElementToBeClickable(driver, mailTo);
        mailTo.sendKeys(addressee);
        waitForElementToBeClickable(driver, mailSubject);
        mailSubject.sendKeys(mailTheme);
        waitForElementToBeClickable(driver, mailBody);
        mailBody.sendKeys(mailText);
        waitForElementToBeClickable(driver, saveMailToDraftButton);
        saveMailToDraftButton.click();
        return this;
    }

    public MailRuMailboxPage openMailboxDraftsFolder() {
        waitForElementToBeClickable(driver, closeNewMailPopUp);
        closeNewMailPopUp.click();
        waitForElementToBeClickable(driver, mailDrafts);
        mailDrafts.click();
        return this;
    }

    public MailRuMailboxPage openMailboxSentFolder() {
        waitForElementToBeClickable(driver, sentMails);
        sentMails.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public MailRuMailboxPage sendMail() {
        waitForRightURL(driver, DRAFT_FOLDER_URL);
        mailsContent.get(0).click();
        waitForElementToBeClickable(driver, sendMailButton);
        sendMailButton.click();
        waitForElementToBeClickable(driver, closeSentMailNotificationPopup);
        closeSentMailNotificationPopup.click();
        return this;
    }

    public boolean isMailVerifiedInMailboxFolder(MailboxFolderEnum mailboxFolder, String mailAddressee, String mailSubject, String mailText) {
        switch (mailboxFolder) {
            case DRAFT:
                waitForRightURL(driver, DRAFT_FOLDER_URL);
                return (mailsContent.get(0).getText().contains(mailAddressee) && mailsContent.get(1).getText().contains(mailSubject) && mailsContent.get(2).getText().contains(mailText));
            case SENT:
                waitForRightURL(driver, SENT_FOLDER_URL);
                return (mailsContent.get(0).getText().contains(mailAddressee) && mailsContent.get(1).getText().contains(mailSubject) && mailsContent.get(2).getText().contains(mailText));
            default :
                return false;
        }
    }

    public boolean isMailPresentInMailboxFolder(MailboxFolderEnum mailboxFolder) {
        switch (mailboxFolder) {
            case DRAFT :
                waitForRightURL(driver, DRAFT_FOLDER_URL);
                waitForTimeInterval(1000);
                return !mailsContent.isEmpty();
            case SENT :
                waitForRightURL(driver, SENT_FOLDER_URL);
                waitForTimeInterval(1000);
                return !mailsContent.isEmpty();
            default :
                return false;
        }
    }
}
