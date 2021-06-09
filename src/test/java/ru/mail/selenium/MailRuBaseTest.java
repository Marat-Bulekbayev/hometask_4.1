package ru.mail.selenium;

import driver.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import pageobject.MailRuMailboxPage;
import pageobject.MailRuMainPage;

import static util.Waiter.*;

public class MailRuBaseTest {

    private static final String USER_MAILBOX_LOGIN = "auto.test94";
    private static final String USER_MAILBOX_PASSWORD = "I(TRc8Rr3vyi";
    private static final WebDriver driver = Driver.getDriver();
    protected MailRuMailboxPage mailRuMailboxPage;

    @BeforeClass
    public void setUp() {
        mailRuMailboxPage = new MailRuMainPage(driver).openPage().loginToMailbox(USER_MAILBOX_LOGIN, USER_MAILBOX_PASSWORD);
        waitImplicitly(driver);
    }

    @AfterTest
    public void cleanUp() {
        mailRuMailboxPage.logOutFromMailbox();
        driver.quit();
    }
}
