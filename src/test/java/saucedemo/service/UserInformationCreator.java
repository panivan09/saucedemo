package saucedemo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import saucedemo.model.UserInformation;

public class UserInformationCreator {

    private static final Logger logger = LogManager.getLogger(UserInformationCreator.class);

    private static final String FIRST_NAME = "testdata.user.info.firstName";
    private static final String LAST_NAME = "testdata.user.info.lastName";
    private static final String POSTAL_CODE = "testdata.user.info.postalCode";

    private UserInformationCreator() {}

    public static UserInformation validUserInformation() {
        String first = TestDataReader.getTestData(FIRST_NAME);
        String last = TestDataReader.getTestData(LAST_NAME);
        String postal = TestDataReader.getTestData(POSTAL_CODE);

        logger.debug("Create user info: firstName='{}', lastName='{}', postalCode='{}'", first, last, postal);

        return new UserInformation(first, last, postal);

    }
}
