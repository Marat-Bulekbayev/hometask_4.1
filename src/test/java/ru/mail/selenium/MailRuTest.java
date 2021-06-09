package ru.mail.selenium;

import enums.MailboxFolderEnum;
import org.testng.Assert;
import org.testng.annotations.Test;


public class MailRuTest extends MailRuBaseTest{

    private static final String ADDRESSEE = "mapat_91@mail.ru";
    private static final String MAIL_THEME = "This e-mail is sent to you by Selenium Auto-Test";
    private static final String MAIL_BODY = "Hello, World!";
    private static final String FAILED_TEST_NAME = "Failed test name: ";

    @Test
    public void testLoginToMailbox() {
        Assert.assertTrue(mailRuMailboxPage
                        .isUserLoggedInMailbox(),
                        FAILED_TEST_NAME + new MailRuTest() {}.getClass().getEnclosingMethod().getName() +
                        ". True - \"write mail\" element is displayed, false is otherwise");
    }

    @Test(dependsOnMethods = {"testLoginToMailbox"})
    public void testWriteNewMail() {
        Assert.assertTrue(mailRuMailboxPage
                        .writeNewMail(ADDRESSEE, MAIL_THEME, MAIL_BODY)
                        .openMailboxDraftsFolder()
                        .isMailPresentInMailboxFolder(MailboxFolderEnum.DRAFT),
                        FAILED_TEST_NAME + new MailRuTest() {}.getClass().getEnclosingMethod().getName() +
                        ". True - draft mail is present in folder, false is otherwise");
    }

    @Test(dependsOnMethods = {"testWriteNewMail"})
    public void testDraftMail() {
        Assert.assertTrue(mailRuMailboxPage
                        .isMailVerifiedInMailboxFolder(MailboxFolderEnum.DRAFT, ADDRESSEE, MAIL_THEME, MAIL_BODY),
                        FAILED_TEST_NAME + new MailRuTest() {}.getClass().getEnclosingMethod().getName() +
                        ". True - draft mail is verified in folder, false is otherwise");
    }

    @Test(dependsOnMethods = {"testDraftMail"})
    public void testSentMailAbsenceInDraftFolder() {
        Assert.assertFalse(mailRuMailboxPage.sendMail()
                        .isMailPresentInMailboxFolder(MailboxFolderEnum.DRAFT),
                        FAILED_TEST_NAME + new MailRuTest() {}.getClass().getEnclosingMethod().getName() +
                        ". False - sent mail is absent in Draft folder, true is otherwise");
    }

    @Test(dependsOnMethods = {"testSentMailAbsenceInDraftFolder"})
    public void testSentMailPresenceInSentFolder() {
        Assert.assertTrue(mailRuMailboxPage
                        .openMailboxSentFolder()
                        .isMailVerifiedInMailboxFolder(MailboxFolderEnum.SENT, ADDRESSEE, MAIL_THEME, MAIL_BODY),
                        FAILED_TEST_NAME + new MailRuTest() {}.getClass().getEnclosingMethod().getName() +
                        ". True - sent mail is verified in Sent folder, false is otherwise");
    }
}
