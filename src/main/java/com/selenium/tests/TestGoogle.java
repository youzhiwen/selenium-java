package com.selenium.tests;

import com.selenium.util.LogType;
import com.selenium.util.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestGoogle {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Logger.Output(LogType.LogTypeName.INFO, "Launch chrome browser.");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Logger.Output(LogType.LogTypeName.INFO, "Set implicit time as 10s.");

        driver.get("https://www.google.com");
        Logger.Output(LogType.LogTypeName.INFO, "Open Google Home page.");
        Logger.Output(LogType.LogTypeName.INFO, driver.getTitle());

        driver.findElement(By.name("q")).sendKeys("Selenium");
        Logger.Output(LogType.LogTypeName.INFO, "Enter keyword selenium.");

        driver.quit();
    }

}
