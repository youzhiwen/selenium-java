package com.selenium.pages;

import com.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id="email")
    WebElement email;

    @FindBy(id="pass")
    WebElement pass;

    @FindBy(id="send2")
    WebElement loginBtn;

    @FindBy(className = "error-msg")
    WebElement errorMsg;

    @FindBy(id="advice-required-entry-email")
    WebElement entryEmail;

    @FindBy(id="advice-validate-email-email")
    WebElement validateEmail;

    @FindBy(id="advice-validate-password-pass")
    WebElement validatePass;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void login(String em, String password){
        type(email,em);
        type(pass,password);
        click(loginBtn);
    }

    public String getErrorMsg(){
        return errorMsg.findElement(By.tagName("span")).getText();
    }

    public String getElementText(WebElement element){
        return "";
    }

    public String getEntryEmailMsg(){
        return this.entryEmail.getText();
    }

    public String getValidateEmailMsg(){
        return this.validateEmail.getText();
    }

    public String getValidatePassMsg(){
        return this.validatePass.getText();
    }

    public String getLoginPageTitle(){
        return this.getCurrentPageTitle();
    }

}
