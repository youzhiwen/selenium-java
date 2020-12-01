package com.selenium.base;

import java.util.Iterator;
import java.util.Set;

import com.selenium.util.LogType;
import com.selenium.util.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {

    public static WebDriver driver;
    public static String pageTitle;
    public static String pageUrl;

    /*
     * constructor method
     */
    public BasePage (WebDriver driver){
        BasePage.driver = driver;
    }

    /*
     *
     */
    protected void type(WebElement element , String text){
        try {
            if (element.isEnabled()) {
                element.clear();
                Logger.Output(LogType.LogTypeName.INFO, "Clean the value if any in "+ element.toString()+".");
                element.sendKeys(text);
                Logger.Output(LogType.LogTypeName.INFO, "Type value is: " + text+".");
            }
        } catch (Exception e) {
            Logger.Output(LogType.LogTypeName.ERROR, e.getMessage()+".");
        }

    }

    /*
     *
     */
    protected void click(WebElement element){

        try {
            if (element.isEnabled()) {
                element.click();
                Logger.Output(LogType.LogTypeName.INFO, "Element: "+element.toString()+" was clicked.");
            }
        } catch (Exception e) {
            Logger.Output(LogType.LogTypeName.ERROR, e.getMessage()+".");
        }

    }

    /*
     *
     */
    protected void clean(WebElement element){

        try {
            if (element.isEnabled()) {
                element.clear();
                Logger.Output(LogType.LogTypeName.INFO, "Element "+element.toString()+" was cleaned.");
            }
        } catch (Exception e) {
            Logger.Output(LogType.LogTypeName.ERROR, e.getMessage()+".");
        }

    }

    /*
     *
     */
    protected void verifyElementIsPresent(WebElement element){

        try {
            if (element.isDisplayed()) {
                Logger.Output(LogType.LogTypeName.INFO, "This Element " + element.toString().trim()+" is present.");

            }
        } catch (Exception e) {
            Logger.Output(LogType.LogTypeName.ERROR, e.getMessage()+".");
        }
    }

    /*
     *
     */
    protected String getCurrentPageTitle(){

        pageTitle=driver.getTitle();
        Logger.Output(LogType.LogTypeName.INFO, "Current page title is "+pageTitle);
        return pageTitle;
    }

    /*
     *
     */
    protected String getCurrentPageUrl(){

        pageUrl=driver.getCurrentUrl();
        Logger.Output(LogType.LogTypeName.INFO, "Current page title is "+pageUrl);
        return pageUrl;
    }

    /*
     *
     */
    public void switchWindow(){

        String currentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> it = handles.iterator();
        while (it.hasNext()) {
            if (currentWindow == it.next()) {
                continue;
            }
            try {
                driver.close();
                WebDriver window = driver.switchTo().window(it.next());
                Logger.Output(LogType.LogTypeName.INFO, "new page title is "+ window.getTitle());
            } catch (Exception e) {
                Logger.Output(LogType.LogTypeName.ERROR,"can not switch to new window."+ e.getMessage());

            }
        }
    }
}
