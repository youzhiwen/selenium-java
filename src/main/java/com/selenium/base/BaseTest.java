package com.selenium.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseTest {
	
	public static WebDriver driver;
	public static Properties prop;
	public String serverURL;
	public String path;

	
	public BaseTest(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/selenium"
					+ "/config/config.properties");
			prop.load(ip);
			serverURL = prop.getProperty("URL");
			path = System.getProperty("user.dir");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @return
	 */
	public WebDriver getDriver(){
		String browser = prop.getProperty("browser");

		if (driver != null) {
			return driver;
		}
		
		if(browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", path + "/driver/chromedriver.exe");
			driver = new ChromeDriver(); 
		}
		else if(browser.equals("ie")){
			System.setProperty("webdriver.ie.driver", path + "/driver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else if(browser.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", path + "/driver/firefoxdriver.exe");
			driver = new FirefoxDriver();
		}else{
			throw new AssertionError(browser + " not supported");
		}

		driver.get(serverURL);
		driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		return driver;
	}

	/**
	 *
	 * @throws InterruptedException
	 */
	public void tearDown() throws InterruptedException{
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * implicit wait
	 * @param time
	 */
	public void callWait(int time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}


	public static void main(String[] args){
		System.out.println(System.getProperty("user.dir")+ "/src/main/java/com/selenium");
	}
	
}
