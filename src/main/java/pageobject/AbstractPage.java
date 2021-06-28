package pageobject;

import businessobject.Email;
import logger.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import util.Waiter;

import java.util.List;

import static util.Waiter.waitForRightURL;

public abstract class AbstractPage {

    @FindBy(xpath = "//a[@title='Написать письмо']")
    protected WebElement writeNewMail;

    @FindBy(xpath = "//div[text()='Входящие']")
    protected WebElement mailInboxFolder;

    @FindBy(xpath = "//div[text()='Черновики']")
    protected WebElement mailDraftsFolder;

    @FindBy(xpath = "//div[text()='Отправленные']")
    protected WebElement mailSentFolder;

    @FindBy(xpath = "//div[@data-testid='whiteline-account']")
    protected WebElement userManager;

    @FindBy(xpath = "//div[text()='Выйти']")
    protected WebElement logOut;

    @FindBy(xpath = "//div[@class='llc__content']/div/span")
    protected List<WebElement> mailsContent;

    protected final By noUnfinishedOrUnsentMailsMessageLocator = By.xpath("//span[@class='octopus__title']");

    protected WebDriver driver;

    protected abstract AbstractPage openPage();

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    protected void writeNewMail(WebElement mailTo, WebElement mailSubject, WebElement mailBody, Email email) {
        click(writeNewMail);
        sendKeys(mailTo, email.getAddressee());
        sendKeys(mailSubject, email.getEmailSubject());
        sendKeys(mailBody, email.getEmailText());
    }

    public MailRuDraftsPage openDrafts() {
        mailDraftsFolder.click();
        return new MailRuDraftsPage(driver);
    }

    public MailRuSentPage openSent() {
        mailSentFolder.click();
        return new MailRuSentPage(driver);
    }

    public void logOutFromMailbox() {
        click(userManager);
        click(logOut);
        MyLogger.info("User is logged out from mailbox");
    }

    public void sendKeys(WebElement element, String text) {
        MyLogger.debug("Highlighting web-element" + element.toString().split("->")[1] + " and entering text: " + text);
        highlightElement(element);
        element.sendKeys(text);
    }

    public void click(WebElement element) {
        MyLogger.debug("Highlighting web-element" + element.toString().split("->")[1] + " and clicking on it");
        highlightElement(element);
        element.click();
    }

    public boolean isUserLoggedInMailbox() {
        return writeNewMail.isDisplayed();
    }

    public boolean isMailContentVerified(Email email) {
        return mailsContent.get(0).getText().contains(email.getAddressee()) &&
                mailsContent.get(1).getText().contains(email.getEmailSubject()) &&
                mailsContent.get(2).getText().contains(email.getEmailText());
    }

    protected boolean isMailPresentInMailboxFolder(String pageUrl) {
        waitForRightURL(driver, pageUrl);
        return !mailsContent.isEmpty();
    }

    public void dragAndDropElement(WebElement element, WebElement target) {
        new Actions(driver).dragAndDrop(element, target).build().perform();
    }

    protected void openContextMenuAndClick(WebElement element, WebElement target) {
        Waiter.waitForElementToBeClickable(driver, element);
        new Actions(driver).contextClick(element).build().perform();
        Waiter.waitForElementToBeClickable(driver, target);
        new Actions(driver).click(target).build().perform();
    }

    protected void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", element);
    }

    protected void unHighlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", element);
    }
}
