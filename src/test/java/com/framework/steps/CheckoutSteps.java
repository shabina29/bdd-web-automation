package com.framework.steps;

import com.framework.pages.CartPage;
import com.framework.pages.CheckoutPage;
import com.framework.pages.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class CheckoutSteps {

    ProductsPage productsPage = new ProductsPage();
    CartPage cartPage = new CartPage();
    CheckoutPage checkoutPage = new CheckoutPage();

    @And("user adds a product to the cart")
    public void user_adds_a_product_to_the_cart() {
        productsPage.addProductToCart();
    }

    @And("user navigates to cart")
    public void user_navigates_to_cart() {
        productsPage.goToCart();
    }

    @And("user proceeds to checkout")
    public void user_proceeds_to_checkout() {
        cartPage.clickCheckout();
    }

    @And("user enters checkout details")
    public void user_enters_checkout_details() {
        checkoutPage.enterCheckoutDetails();
    }

    @And("user completes the checkout")
    public void user_completes_the_checkout() {
        checkoutPage.finishCheckout();
    }

    @Then("order confirmation message should be displayed")
    public void order_confirmation_message_should_be_displayed() {
        Assert.assertTrue(
                checkoutPage.isOrderConfirmed(),
                "Order confirmation message not displayed"
        );
    }
}
