package com.betbull.step_definitions;

import com.betbull.pages.HomePage;
import com.betbull.pages.LoginPage;
import com.betbull.utilities.BrowserUtils;
import com.betbull.utilities.ConfigurationReader;
import com.betbull.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class SignInStepDefs {

    LoginPage loginPage = new LoginPage();

    @Given("User is on the sign in page")
    public void user_is_on_the_sign_in_page() {

        String url= ConfigurationReader.get("url");
        Driver.get().get(url);

        Driver.get().manage().window().maximize();

        HomePage homePage = new HomePage();

        BrowserUtils.waitForClickablility(homePage.signInButton,5);

        homePage.signInButton.click();
    }

    @When("User signs in without email and password")
    public void user_signs_in_without_email_and_password() {

        BrowserUtils.waitForClickablility(loginPage.loginButton,5);

        loginPage.loginButton.click();
    }

    @Then("User should see the {string} message")
    public void user_should_see_the_message(String expectedMessage) {

        String actualMessage = loginPage.getActualMessage(expectedMessage);

        assertEquals(expectedMessage,actualMessage);
    }

    @When("User signs in with just email")
    public void user_signs_in_with_just_email() {

        BrowserUtils.waitForClickablility(loginPage.emailInputBox,5);

        loginPage.emailInputBox.sendKeys(ConfigurationReader.get("email"));
        loginPage.loginButton.click();
    }

    @When("User signs in with just password")
    public void user_signs_in_with_just_password() {

        BrowserUtils.waitForClickablility(loginPage.passwordInputBox,5);

        loginPage.passwordInputBox.sendKeys(ConfigurationReader.get("password"));
        loginPage.loginButton.click();
    }

    @When("User signs in with incorrect credentials")
    public void user_signs_in_with_incorrect_credentials() {

        BrowserUtils.waitForClickablility(loginPage.emailInputBox,5);

        loginPage.emailInputBox.sendKeys(ConfigurationReader.get("email"));
        loginPage.passwordInputBox.sendKeys(ConfigurationReader.get("password"));
        loginPage.loginButton.click();
    }

}
