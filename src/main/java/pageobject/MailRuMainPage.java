package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static util.Waiter.*;

public class MailRuMainPage {

    private static final String PAGE_URL = "https://mail.ru/";
    private WebDriver driver;

    @FindBy(xpath = "//input[@name='login']")
    WebElement userMailboxLogin;

    @FindBy(xpath = "//button[@data-testid='enter-password']")
    WebElement enterPasswordButton;

    @FindBy(xpath = "//input[@name='password']")
    WebElement userMailboxPassword;

    @FindBy(xpath = "//button[@data-testid='login-to-mail']")
    WebElement enterButton;

    public MailRuMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public MailRuMainPage openPage() {
        driver.get(PAGE_URL);
        return this;
    }

    public MailRuMailboxPage loginToMailbox(String userLogin, String userPassword) {
        userMailboxLogin.sendKeys(userLogin);
        waitForElementToBeClickable(driver, enterPasswordButton);
        enterPasswordButton.click();
        waitForElementToBeClickable(driver, userMailboxPassword);
        userMailboxPassword.sendKeys(userPassword);
        waitForElementToBeClickable(driver, enterButton);
        enterButton.click();
        return new MailRuMailboxPage(driver);
    }
}
