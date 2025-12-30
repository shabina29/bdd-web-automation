package com.framework.pages;

import com.framework.action.ActionUtil;
import org.openqa.selenium.By;

public class CheckoutPage {

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");

    private By confirmationHeader =
            By.className("complete-header");

    private By errorMessage =
            By.cssSelector("h3[data-test='error']");

    // ---------- POSITIVE FLOW ----------
    public void enterCheckoutDetails() {

        ActionUtil.type(firstName, "John");
        ActionUtil.type(lastName, "Doe");
        ActionUtil.type(postalCode, "12345");

        ActionUtil.click(continueButton);
    }

    public void finishCheckout() {
        ActionUtil.click(finishButton);
    }

    public boolean isOrderConfirmed() {
        return ActionUtil.isDisplayed(confirmationHeader);
    }

    // ---------- NEGATIVE FLOW ----------
    public void continueWithoutDetails() {
        ActionUtil.click(continueButton);
    }

    public String getCheckoutErrorMessage() {
        return ActionUtil.getText(errorMessage);
    }
}
