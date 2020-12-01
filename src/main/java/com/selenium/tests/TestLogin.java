package com.selenium.tests;

import com.selenium.base.BrowserEngine;
import com.selenium.util.LogType;
import com.selenium.util.Logger;
import com.selenium.pages.HomePage;
import com.selenium.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;


public class TestLogin{

    public WebDriver driver;
    WebDriverWait waitVar = null;

    @BeforeClass
    public void setUp() throws IOException, InterruptedException {

        BrowserEngine browserEngine = new BrowserEngine();
        browserEngine.initConfigData();
        driver=browserEngine.getBrowser();

        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.clickLogin();
    }

    @Test(description = "Fail1: login with empty username and password")
    public void loginFail1(){
        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        loginPage.login("","");
        Assert.assertTrue(loginPage.getEntryEmailMsg().equalsIgnoreCase("This is a required field."));
    }

    /**
     * Fail2: login with a invalid email address
     */
    @Test
    public void loginFail2(){
        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        loginPage.login("test@1.1","11111111");
        Logger.Output(LogType.LogTypeName.INFO,loginPage.getValidateEmailMsg());
        Assert.assertTrue(loginPage.getValidateEmailMsg().equalsIgnoreCase("Please enter a valid email address. For example johndoe@domain.com."));
    }

    /**
     * Fail3: login with a valid email and a invalid password
     */
    @Test
    public void loginFail3(){
        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        loginPage.login("zhiwen.you@gmail.com","11");
        Logger.Output(LogType.LogTypeName.INFO,loginPage.getValidatePassMsg());
        Assert.assertTrue(loginPage.getValidatePassMsg().equalsIgnoreCase("Please enter 6 or more characters without leading or trailing spaces."));

    }

    /**
     * login with a invalid user and password
     * @throws InterruptedException
     */
    @Test
    public void loginFail() throws InterruptedException{
        LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
        loginPage.login("zhiwen.you@gmail.com","123456");
        Logger.Output(LogType.LogTypeName.INFO,loginPage.getErrorMsg());
        Assert.assertTrue(loginPage.getErrorMsg().equalsIgnoreCase("Invalid login or password."));
    }

    @AfterClass
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

}
