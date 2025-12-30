package com.framework.manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initialize WebDriver based on browser name
     */
    public static void initDriver(String browser) {

        if (driver.get() == null) {

            switch (browser.toLowerCase()) {

                case "chrome":
                    driver.set(new ChromeDriver());
                    break;

                case "edge":
                    driver.set(new EdgeDriver());
                    break;

                default:
                    throw new RuntimeException(
                            "Browser not supported: " + browser
                    );
            }

            driver.get().manage().window().maximize();
        }
    }

    /**
     * Get current WebDriver
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quit browser and clean ThreadLocal
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
