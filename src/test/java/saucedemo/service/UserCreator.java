package saucedemo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import saucedemo.model.User;

public class UserCreator {

    private static final Logger logger = LogManager.getLogger(UserCreator.class);

    private static final String STANDARD_USER = "testdata.user.standard.username";
    private static final String LOCKED_OUT_USER = "testdata.user.locked.username";
    private static final String PASSWORD = "testdata.user.standard.password";
    public static final String WRONG_PASSWORD = "testdata.user.wrong.password";
    public static final String WRONG_USER = "testdata.user.wrong.username";
    public static final String EMPTY_FIELD = "";

    public static User standardUser() {
        String username = TestDataReader.getTestData(STANDARD_USER);
        logger.debug("Create user: STANDARD (username={})", username);

        return new User(username, TestDataReader.getTestData(PASSWORD));
    }

    public static User lockedOutUser() {
        String username = TestDataReader.getTestData(LOCKED_OUT_USER);
        logger.debug("Create user: LOCKED_OUT (username={})", username);

        return new User(username, TestDataReader.getTestData(PASSWORD));
    }

    public static User standardUserWithWrongPassword() {
        String username = TestDataReader.getTestData(STANDARD_USER);
        logger.debug("Create user: STANDARD + WRONG_PASSWORD (username={})", username);

        return new User(username, TestDataReader.getTestData(WRONG_PASSWORD));
    }

    public static User wrongUserWithCorrectPassword() {
        String username = TestDataReader.getTestData(WRONG_USER);
        logger.debug("Create user: WRONG_USERNAME + CORRECT_PASSWORD (username={})", username);

        return new User(username, TestDataReader.getTestData(PASSWORD));
    }

    public static User invalidUser() {
        String username = TestDataReader.getTestData(WRONG_USER);
        logger.debug("Create user: WRONG_USERNAME + WRONG_PASSWORD (username={})", username);

        return new User(username, TestDataReader.getTestData(WRONG_PASSWORD));
    }

    public static User withEmptyUsername() {
        logger.debug("Create user: EMPTY_USERNAME");

        return new User(EMPTY_FIELD, TestDataReader.getTestData(PASSWORD));
    }

    public static User withEmptyPassword() {
        String username = TestDataReader.getTestData(STANDARD_USER);
        logger.debug("Create user: EMPTY_PASSWORD (username={})", username);

        return new User(username, EMPTY_FIELD);
    }

    public static User withEmptyUsernameAndPassword() {
        logger.debug("Create user: EMPTY_USERNAME_AND_PASSWORD");

        return new User(EMPTY_FIELD, EMPTY_FIELD);
    }
}
