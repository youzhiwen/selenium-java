package com.selenium.pages;

import com.selenium.base.BasePage;
import net.bytebuddy.implementation.bytecode.assign.TypeCasting;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage {

	@FindBy(xpath = "//*[@id=\"header\"]/div/div[2]/div/a/span[2]")
	WebElement accountLink;

	@FindBy (xpath="/html/body/div[1]/div/header/div/div[5]/div/ul/li[6]/a")
	WebElement loginLink;


	public HomePage(WebDriver driver) {
		super(driver);
	}

	public String getHomePageTitle(){
		return this.getCurrentPageTitle();
	}

	/*
	 * click login button
	 */
	public LoginPage clickLogin(){
		this.clickAccount();
		click(loginLink);
		return new LoginPage(driver);
	}

	public void clickAccount(){
		click(accountLink);
	}

}
