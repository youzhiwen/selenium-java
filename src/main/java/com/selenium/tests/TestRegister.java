package com.selenium.tests;

import com.selenium.base.BrowserEngine;
import com.selenium.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class TestRegister {
    public WebDriver driver;

    @BeforeClass
    public void setUp() throws IOException, InterruptedException {

        BrowserEngine browserEngine = new BrowserEngine();
        browserEngine.initConfigData();
        driver=browserEngine.getBrowser();

        HomePage homePage = PageFactory.initElements(driver,HomePage.class);
        homePage.clickLogin();
        Thread.sleep(1000);
    }



    @AfterClass
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
