package test;

import enums.MailboxFolder;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobject.MailRuMailboxPage;
import pageobject.MailRuMainPage;

public class MailRuTest extends MailRuBaseTest {

    private static final String USER_MAILBOX_LOGIN = "auto.test94";
    private static final String USER_MAILBOX_PASSWORD = "I(TRc8Rr3vyi";
    private static final String ADDRESSEE = "mapat_91@mail.ru";
    private static final String MAIL_THEME = "This e-mail is sent to you by Selenium Auto-Test";
    private static final String MAIL_BODY = "Hello, World!";

    private MailRuMailboxPage mailRuMailboxPage;

    @Test
    public void testLoginToMailbox() {
        mailRuMailboxPage = new MailRuMainPage(driver).openPage().loginToMailbox(USER_MAILBOX_LOGIN, USER_MAILBOX_PASSWORD);
        boolean isUserLoggedIn = mailRuMailboxPage.isUserLoggedInMailbox();
        Assert.assertTrue(isUserLoggedIn, "User should be logged in mailbox");
    }

    @Test(dependsOnMethods = {"testLoginToMailbox"})
    public void testNewMailPresenceInDrafts() {
        boolean isMailPresent = mailRuMailboxPage
                .writeNewDraftMail(ADDRESSEE, MAIL_THEME, MAIL_BODY)
                .openMailboxFolder(MailboxFolder.DRAFT)
                .isMailPresentInMailboxFolder(MailboxFolder.DRAFT);

        Assert.assertTrue(isMailPresent, "New saved mail must be in drafts folder");
    }

    @Test(dependsOnMethods = {"testNewMailPresenceInDrafts"})
    public void testDraftMailContent() {
        boolean isMailContentVerified = mailRuMailboxPage.isMailContentVerified(ADDRESSEE, MAIL_THEME, MAIL_BODY);

        Assert.assertTrue(isMailContentVerified, "Draft mail content must match entered data (addressee, mail subject and text)");
    }

    @Test(dependsOnMethods = {"testDraftMailContent"})
    public void testSentMailAbsenceInDraftFolder() {
        boolean isMailAbsent = mailRuMailboxPage.sendDraftMail().isMailAbsentInMailboxFolder();

        Assert.assertFalse(isMailAbsent, "Sent mail must disappear from draft folder");
    }

    @Test(dependsOnMethods = {"testSentMailAbsenceInDraftFolder"})
    public void testSentMailPresenceInSentFolder() {
        boolean isMailPresent = mailRuMailboxPage.openMailboxFolder(MailboxFolder.SENT).isMailPresentInMailboxFolder(MailboxFolder.SENT);

        mailRuMailboxPage.logOutFromMailbox();

        Assert.assertTrue(isMailPresent, "Sent mail must be present in sent folder");
    }
}
