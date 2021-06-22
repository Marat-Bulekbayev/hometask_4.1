package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PassportYandexPage extends AbstractPage {

    private static final String PAGE_URL = "https://passport.yandex.ru/";

    @FindBy(xpath = "//input[@name='login']")
    WebElement userLogin;

    @FindBy(xpath = "//input[@name='passwd']")
    WebElement userPassword;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement enterButton;

    @FindBy(xpath = "//div[@data-t='phone_skip']/button")
    WebElement bindPhoneSkipButton;

    public PassportYandexPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public PassportYandexPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public YandexDiskFilesPage enterCredentials(String login, String password) {
        userLogin.sendKeys(login);
        enterButton.click();
        userPassword.sendKeys(password);
        enterButton.click();
        return new YandexDiskFilesPage(driver);
    }
}
