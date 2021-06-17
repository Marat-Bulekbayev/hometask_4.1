package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YandexDiskHomePage extends AbstractPage {

    private static final String PAGE_URL = "https://disk.yandex.ru/";

    @FindBy(xpath = "//a[@class='button button_login header__login-link']")
    WebElement enterButton;

    public YandexDiskHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public YandexDiskHomePage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public PassportYandexPage loginToDisk() {
        enterButton.click();
        return new PassportYandexPage(driver);
    }
}
