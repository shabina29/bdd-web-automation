package com.framework.steps;

import com.framework.pages.LoginPage;
import com.framework.pages.ProductsPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class SauceDemoSteps {

    LoginPage loginPage = new LoginPage();
    ProductsPage productsPage = new ProductsPage();

    @Given("user is on SauceDemo login page")
    public void user_is_on_login_page() {
        // URL is launched in Hooks using config.properties
    }

    @When("user enters valid username and password")
    public void user_enters_credentials() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
    }

    @And("clicks on login button")
    public void clicks_login_button() {
        loginPage.clickLogin();
    }

    @Then("Products page should be displayed")
    public void products_page_should_be_displayed() {
        Assert.assertTrue(
                productsPage.isProductsPageDisplayed(),
                "Login failed: Products page not displayed"
        );
    }
    
    @Given("user is on SauceDemo login page for negative scenario")
    public void user_is_on_saucedemo_login_page_for_negative_scenario() {
		// URL is launched in Hooks using config.properties
	}
    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
		loginPage.enterUsername("invalid_user");
		loginPage.enterPassword("invalid_pass");
	}
    @Then("Error message should be displayed")
	public void error_message_should_be_displayed() {
		String expectedMessage = "Epic sadface: Username and password do not match any user in this service";
		Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage, "Error message mismatch");
	}
    
    
}
