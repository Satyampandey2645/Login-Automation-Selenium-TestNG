package com.login.automation.pages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.nio.file.Files;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    
    private WebDriver driver;
    
    // Locators
    @FindBy(id = "username")
    private WebElement usernameField;
    
    @FindBy(id = "password")
    private WebElement passwordField;
    
    @FindBy(id = "submit")
    private WebElement submitButton;
    
    @FindBy(className = "post-title")
    private WebElement successMessage;
    
    @FindBy(id = "error")
    private WebElement errorMessage;
    
    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // Methods
    public void enterUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    
    public void enterPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    
    public void clickSubmit() {
        submitButton.click();
    }
    
    public String getSuccessMessage() {
        return successMessage.getText();
    }
    
    public String getErrorMessage() {
        return errorMessage.getText();
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
    }

    public void takeScreenshot(String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String filePath = "screenshots/" + testName + "_" + timestamp + ".png";
        try {
            // Create the screenshots directory if it doesn't exist
            Files.createDirectories(Paths.get("screenshots"));
            FileUtils.copyFile(srcFile, new File(filePath));
            System.out.println("Screenshot saved to: " + filePath);
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
}