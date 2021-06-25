package pageobject;

import businessobject.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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

    @FindBy(xpath = "//input[@id='q']")
    private WebElement searchTextField;

    @FindBy(xpath = "//button[@data-testid='search-button']")
    private WebElement searchButton;

    @FindBy(xpath = "//h3[@class]")
    private List<WebElement> searchResultLinks;

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

    public MailRuMainPage search(String searchQuery) {
        searchTextField.sendKeys(searchQuery);
        searchButton.click();
        return this;
    }

    public boolean isSearchResultDisplayed(String searchQuery) {
        return searchResultLinks.get(0).getText().contains(searchQuery);
    }
}
