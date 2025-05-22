package com.login.automation.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.login.automation.pages.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().browserVersion("137.0.7151.41").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://practicetestautomation.com/practice-test-login/");
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1, description = "Test successful login with valid credentials")
    public void testSuccessfulLogin() {
        loginPage.login("student", "Password123");

        // Verify success message
        String expectedMessage = "Logged In Successfully";
        String actualMessage = loginPage.getSuccessMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Login was not successful");
    }

    @Test(priority = 2, description = "Test login with invalid username")
    public void testInvalidUsername() {
        loginPage.login("invalidUser", "Password123");

        // Verify error message
        String expectedMessage = "Your username is invalid!";
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Error message for invalid username is incorrect");
    }

    @Test(priority = 3, description = "Test login with invalid password")
    public void testInvalidPassword() {
        loginPage.login("student", "invalidPassword");

        // Verify error message
        String expectedMessage = "Your password is invalid!";
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Error message for invalid password is incorrect");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            loginPage.takeScreenshot(result.getName());
        }

        if (driver != null) {
            driver.quit();
        }
    }
}