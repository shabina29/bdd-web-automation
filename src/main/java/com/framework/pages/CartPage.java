package com.framework.pages;

import com.framework.action.ActionUtil;
import org.openqa.selenium.By;

public class CartPage {

    // Cart page unique elements
    private By cartTitle = By.className("title");   // "Your Cart"
    private By checkoutButton = By.id("checkout");

    public void waitForCartPage() {
        ActionUtil.isDisplayed(cartTitle);
    }

    public void clickCheckout() {
        waitForCartPage();              // ✅ wait for cart page to load
        ActionUtil.click(checkoutButton);
    }
}
