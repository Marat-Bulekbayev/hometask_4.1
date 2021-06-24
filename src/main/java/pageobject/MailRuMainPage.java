package pageobject;

import businessobject.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailRuMainPage extends AbstractPage {

    private static final String PAGE_URL = "https://mail.ru/";

    @FindBy(xpath = "//input[@name='login']")
    private WebElement userMailboxLogin;

    @FindBy(xpath = "//button[@data-testid='enter-password']")
    private WebElement enterPasswordButton;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement userMailboxPassword;

    @FindBy(xpath = "//button[@data-testid='login-to-mail']")
    private WebElement enterButton;

    public MailRuMainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MailRuMainPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public MailRuInboxPage loginToMailbox(User user) {
        userMailboxLogin.sendKeys(user.getName());
        enterPasswordButton.click();
        userMailboxPassword.sendKeys(user.getPassword());
        enterButton.click();
        return new MailRuInboxPage(driver);
    }
}
