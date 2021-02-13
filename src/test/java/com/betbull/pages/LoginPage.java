package com.betbull.pages;

import com.betbull.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(id = "email")
    public WebElement emailInputBox;

    @FindBy(id = "passwd")
    public WebElement passwordInputBox;

    @FindBy(id = "SubmitLogin")
    public WebElement loginButton;

    public String getActualMessage(String expectedMessage){

        WebElement errorMessage = Driver.get().findElement(By.xpath("//li[text()='" + expectedMessage +"']"));

        String actualMessage = errorMessage.getText();

        return actualMessage;
    }
}
