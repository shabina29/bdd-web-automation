package com.framework.pages;

import com.framework.action.ActionUtil;
import org.openqa.selenium.By;

public class LoginPage {

    private By username = By.id("user-name");
    private By password = By.id("password");
    private By loginButton = By.id("login-button");

    private By productsTitle = By.className("title");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    // -------- Login Actions --------

    public void enterUsername(String user) {
        ActionUtil.type(username, user);
    }

    public void enterPassword(String pass) {
        ActionUtil.type(password, pass);
    }

    public void clickLogin() {
        ActionUtil.click(loginButton);
    }

    // -------- Validations --------

    public boolean isLoginSuccessful() {
        return ActionUtil.isDisplayed(productsTitle);
    }

    public String getErrorMessage() {
        return ActionUtil.getText(errorMessage);
    }
}


