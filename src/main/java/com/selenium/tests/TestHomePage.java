package com.selenium.tests;

import com.selenium.base.BrowserEngine;
import com.selenium.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestHomePage {
    public WebDriver driver;

    @BeforeClass
    public void setUp() throws IOException {

        BrowserEngine browserEngine = new BrowserEngine();
        browserEngine.initConfigData();
        driver=browserEngine.getBrowser();

    }

    @Test
    public void testHomePage(){
        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        Assert.assertTrue(homePage.getHomePageTitle().equalsIgnoreCase("Home page"));
    }

    @AfterClass
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
