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

        Assert.assertTrue(
                productsPage.isProductAddedToCart(),
                "Product was NOT added to cart"
        );
    }
    @And("user navigates to cart")
    public void user_navigates_to_cart() {
        productsPage.goToCart();

        Assert.assertTrue(
                cartPage.isProductPresentInCart(),
                "Product not found in cart"
        );
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

    // -------- Negative Flow --------

    @And("user continues checkout without entering details")
    public void user_continues_checkout_without_entering_details() {
        checkoutPage.continueWithoutDetails();
    }

    @Then("error message should be displayed on checkout page")
    public void error_message_should_be_displayed_on_checkout_page() {
        Assert.assertEquals(
                checkoutPage.getCheckoutErrorMessage(),
                "Error: First Name is required",
                "Checkout validation error message mismatch"
        );
    }
}
