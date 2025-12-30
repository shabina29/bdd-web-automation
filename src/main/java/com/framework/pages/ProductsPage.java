package com.framework.pages;

import com.framework.action.ActionUtil;
import org.openqa.selenium.By;

public class ProductsPage {

    private By productsTitle =
            By.xpath("//span[text()='Products']");

    private By addToCartBackpack =
            By.id("add-to-cart-sauce-labs-backpack");

    private By cartIcon =
            By.className("shopping_cart_link");

    public boolean isProductsPageDisplayed() {
        return ActionUtil.isDisplayed(productsTitle);
    }

    public void addProductToCart() {
        ActionUtil.click(addToCartBackpack);
    }

    public void goToCart() {
        ActionUtil.click(By.className("shopping_cart_link"));
    }

}
