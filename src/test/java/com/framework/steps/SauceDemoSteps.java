package com.framework.steps;

import com.framework.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class SauceDemoSteps {

    LoginPage loginPage = new LoginPage();

    @Given("user is on SauceDemo login page")
    public void user_is_on_sauce_demo_login_page() {
        // URL is launched via Hooks using config.properties
        // This step keeps feature file readable
    }

    @And("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
    }

    @And("clicks on login button")
    public void clicks_on_login_button() {
        loginPage.clickLogin();
    }

    @Then("Products page should be displayed")
    public void products_page_should_be_displayed() {
        Assert.assertTrue(
                loginPage.isLoginSuccessful(),
                "Login failed: Products page not displayed"
        );
    }

    // -------- Negative Login --------

    @And("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("wrong_password");
    }

    @Then("login error message should be displayed")
    public void login_error_message_should_be_displayed() {
        Assert.assertEquals(
                loginPage.getErrorMessage(),
                "Epic sadface: Username and password do not match any user in this service",
                "Login error message mismatch"
        );
    }
}

