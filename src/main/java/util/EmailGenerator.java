package util;

import businessobject.Email;

public class EmailGenerator {

    private static final String ADDRESSEE = "mapat_91@mail.ru";
    private static final String MAIL_THEME = "This e-mail is sent to you by Selenium Auto-Test";
    private static final String MAIL_BODY = "Hello, World!";

    private EmailGenerator(){}

    public static Email generateEmail() {
        return new Email(ADDRESSEE, MAIL_THEME, MAIL_BODY);
    }
}
