package test;

import businessobject.Email;
import businessobject.User;
import enums.MailboxFolder;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pageobject.MailRuMailboxPage;
import pageobject.MailRuMainPage;
import util.EmailGenerator;
import util.UserCreator;

public class MailRuTest extends MailRuBaseTest {

    private static final User MAIL_RU_TEST_USER = UserCreator.createUser();
    private static final Email TEST_EMAIL = EmailGenerator.generateEmail();
    private MailRuMailboxPage mailRuMailboxPage;

    @Test
    public void testLoginToMailbox() {
        mailRuMailboxPage = new MailRuMainPage(driver).openPage().loginToMailbox(MAIL_RU_TEST_USER);
        boolean isUserLoggedIn = mailRuMailboxPage.isUserLoggedInMailbox();
        Assert.assertTrue(isUserLoggedIn, "User should be logged in mailbox");
    }

    @Test(dependsOnMethods = {"testLoginToMailbox"})
    public void testNewMailPresenceInDrafts() {
        boolean isMailPresent = mailRuMailboxPage
                .writeNewDraftMail(TEST_EMAIL)
                .openMailboxFolder(MailboxFolder.DRAFT)
                .isMailPresentInMailboxFolder(MailboxFolder.DRAFT);

        Assert.assertTrue(isMailPresent, "New saved mail must be in drafts folder");
    }

    @Test(dependsOnMethods = {"testNewMailPresenceInDrafts"})
    public void testDraftMailContent() {
        boolean isMailContentVerified = mailRuMailboxPage.isMailContentVerified(TEST_EMAIL);

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

        Assert.assertTrue(isMailPresent, "Sent mail must be present in sent folder");
    }

    @AfterClass
    public void logout() {
        mailRuMailboxPage.logOutFromMailbox();
    }
}
