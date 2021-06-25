package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MailRuSentPage extends AbstractPage {

    private static final String PAGE_URL = "https://e.mail.ru/sent/";

    public MailRuSentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MailRuSentPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    public boolean isMailPresentInSent() {
        return isMailPresentInMailboxFolder(PAGE_URL);
    }
}
