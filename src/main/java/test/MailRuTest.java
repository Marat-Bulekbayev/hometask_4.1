package test;

import businessobject.Email;
import businessobject.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pageobject.MailRuInboxPage;
import pageobject.MailRuMainPage;
import util.EmailGenerator;
import util.UserCreator;

public class MailRuTest extends MailRuBaseTest {

    private static final User MAIL_RU_TEST_USER = UserCreator.createUser();
    private static final Email TEST_EMAIL = EmailGenerator.generateEmail();
    private MailRuInboxPage mailRuPage;

    @Test
    public void testLoginToMailbox() {
        mailRuPage = new MailRuMainPage(driver).openPage().loginToMailbox(MAIL_RU_TEST_USER);
        boolean isUserLoggedIn = mailRuPage.isUserLoggedInMailbox();
        Assert.assertTrue(isUserLoggedIn, "User should be logged in mailbox");
    }

    @Test(dependsOnMethods = {"testLoginToMailbox"})
    public void testNewMailPresenceInDrafts() {
        boolean isMailPresent = mailRuPage.writeNewDraftMail(TEST_EMAIL).openDrafts().isMailPresentInDrafts();

        Assert.assertTrue(isMailPresent, "New saved mail must be in drafts folder");
    }

    @Test(dependsOnMethods = {"testNewMailPresenceInDrafts"})
    public void testDraftMailContent() {
        boolean isMailContentVerified = mailRuPage.isMailContentVerified(TEST_EMAIL);

        Assert.assertTrue(isMailContentVerified, "Draft mail content must match entered data (addressee, mail subject and text)");
    }

    @Test(dependsOnMethods = {"testDraftMailContent"})
    public void testSentMailAbsenceInDraftFolder() {
        boolean isMailAbsent = mailRuPage.openDrafts().sendDraftMail().isMailAbsentInDrafts();

        Assert.assertFalse(isMailAbsent, "Sent mail must disappear from draft folder");
    }

    @Test(dependsOnMethods = {"testSentMailAbsenceInDraftFolder"})
    public void testSentMailPresenceInSentFolder() {
        boolean isMailPresent = mailRuPage.openSent().isMailPresentInSent();

        Assert.assertTrue(isMailPresent, "Sent mail must be present in sent folder");
    }

    @AfterClass
    public void logout() {
        mailRuPage.logOutFromMailbox();
    }
}
