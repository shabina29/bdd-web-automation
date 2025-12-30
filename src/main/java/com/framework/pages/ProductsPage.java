package com.framework.pages;

import com.framework.action.ActionUtil;
import org.openqa.selenium.By;

public class ProductsPage {

    private By addToCartButton =
            By.id("add-to-cart-sauce-labs-backpack");

    private By cartIcon =
            By.className("shopping_cart_link");

    private By removeButton =
            By.id("remove-sauce-labs-backpack");

    public void addProductToCart() {
        ActionUtil.click(addToCartButton);
    }

    // 🔥 THIS CONFIRMS PRODUCT WAS ADDED
    public boolean isProductAddedToCart() {
        return ActionUtil.isDisplayed(removeButton);
    }

    public void goToCart() {
        ActionUtil.click(cartIcon);
    }
}

