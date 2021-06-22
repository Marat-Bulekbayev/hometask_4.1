package util;

import businessobject.User;

public class UserCreator {

    private static final String USER_NAME = "auto.test94";
    private static final String USER_PASSWORD = "I(TRc8Rr3vyi";

    private UserCreator(){}

    public static User createUser() {
        return new User(USER_NAME, USER_PASSWORD);
    }
}
