package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailRuMainPage extends AbstractPage {

    private static final String PAGE_URL = "https://mail.ru/";

    @FindBy(xpath = "//input[@name='login']")
    WebElement userMailboxLogin;

    @FindBy(xpath = "//button[@data-testid='enter-password']")
    WebElement enterPasswordButton;

    @FindBy(xpath = "//input[@name='password']")
    WebElement userMailboxPassword;

    @FindBy(xpath = "//button[@data-testid='login-to-mail']")
    WebElement enterButton;

    public MailRuMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MailRuMainPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public MailRuMailboxPage loginToMailbox(String userLogin, String userPassword) {
        userMailboxLogin.sendKeys(userLogin);
        enterPasswordButton.click();
        userMailboxPassword.sendKeys(userPassword);
        enterButton.click();
        return new MailRuMailboxPage(driver);
    }
}
