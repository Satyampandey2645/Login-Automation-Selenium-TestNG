package com.login.automation;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test(priority = 1, description = "Test successful login with valid credentials")
    public void testSuccessfulLogin() {
        LoggerUtil.logInfo("Starting testSuccessfulLogin");
        loginPage.login("student", "Password123");

        // Verify success message
        String expectedMessage = "Logged In Successfully";
        String actualMessage = loginPage.getSuccessMessage();
        LoggerUtil.logInfo("Verifying success message");
        Assert.assertEquals(actualMessage, expectedMessage, "Login was not successful");
        LoggerUtil.logInfo("testSuccessfulLogin completed successfully");
    }

}
