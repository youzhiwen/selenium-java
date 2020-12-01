package com.selenium.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.selenium.util.LogType;
import com.selenium.util.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserEngine {
    public String browserName;
    public String serverURL;
    public WebDriver driver;
    public String path;

    public void initConfigData() throws IOException{

        Properties p = new Properties();
        // load property file
        InputStream ips = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/selenium"
                + "/config/config.properties");
        p.load(ips);

        path = System.getProperty("user.dir");

        Logger.Output(LogType.LogTypeName.INFO, "Start to select browser name from properties file");
        browserName=p.getProperty("browser");
        Logger.Output(LogType.LogTypeName.INFO, "Your had select test browser type is: "+ browserName);
        serverURL = p.getProperty("serverURL");
        Logger.Output(LogType.LogTypeName.INFO, "The test server URL is: "+ serverURL);
        ips.close();

    }

    public WebDriver getBrowser(){

        if(browserName.equalsIgnoreCase("Firefox")){

            System.setProperty("webdriver.gecko.driver", path + "/driver/geckodriver.exe");
            driver = new FirefoxDriver();

            Logger.Output(LogType.LogTypeName.INFO, "Launching Firefox ...");

        }else if(browserName.equalsIgnoreCase("Chrome")){

            System.setProperty("webdriver.chrome.driver", path + "/driver/chromedriver.exe");
            driver= new ChromeDriver();
            Logger.Output(LogType.LogTypeName.INFO, "Launching Chrome ...");

        }else if(browserName.equalsIgnoreCase("IE")){

            System.setProperty("webdriver.ie.driver", path + "/driver/IEDriverServer.exe");
            driver= new InternetExplorerDriver();
            Logger.Output(LogType.LogTypeName.INFO, "Launching IE ...");
        }

        driver.get(serverURL);
        Logger.Output(LogType.LogTypeName.INFO, "Open URL: "+ serverURL);
        driver.manage().window().maximize();
        Logger.Output(LogType.LogTypeName.INFO, "Maximize browser...");
        callWait(5);
        return driver;
    }

    /*
     * close browser,then quit
     */

    public void tearDown() throws InterruptedException{

        Logger.Output(LogType.LogTypeName.INFO, "Closing browser...");
        driver.quit();
        Thread.sleep(3000);
    }

    /*
     * implicit time
     */
    public void callWait(int time){

        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
        Logger.Output(LogType.LogTypeName.INFO, "Wait for "+time+" seconds.");
    }

}

