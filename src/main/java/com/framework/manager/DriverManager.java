package com.framework.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Initialize WebDriver
    public static void initDriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            // 🔴 Disable Chrome password manager & breach alerts
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", prefs);

            // 🔒 Extra stability flags (VERY IMPORTANT)
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-save-password-bubble");

            // 🧹 Always start with fresh browser profile
            options.addArguments("--incognito");

            // Optional but helpful
            options.addArguments("--disable-extensions");
            options.addArguments("--start-maximized");

            driver.set(new ChromeDriver(options));
        }

        getDriver().manage().window().maximize();
    }

    // Get WebDriver instance
    public static WebDriver getDriver() {
        return driver.get();
    }

    // Quit WebDriver
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
