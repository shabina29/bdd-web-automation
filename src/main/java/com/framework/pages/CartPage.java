package com.framework.pages;

import com.framework.action.ActionUtil;
import org.openqa.selenium.By;

public class CartPage {

    private By checkoutButton = By.id("checkout");

    // 🔥 Correct cart item locator
    private By cartItemName =
            By.className("inventory_item_name");

    public boolean isProductPresentInCart() {
        return ActionUtil.isDisplayed(cartItemName);
    }

    public void clickCheckout() {
        ActionUtil.click(checkoutButton);
    }
}





