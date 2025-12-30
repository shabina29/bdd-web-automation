package com.framework.base;

import com.framework.manager.DriverManager;
import com.framework.utils.ConfigReader;
import com.framework.utils.ExtentTestManager;
import com.framework.utils.ScreenshotUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Hooks.java
 * ----------
 * Purpose:
 * Controls the complete test lifecycle in Cucumber.
 *
 * @Before  → Browser + Report setup
 * @After   → Screenshot + Report update + Browser cleanup
 */
public class Hooks {

    /**
     * Runs BEFORE every scenario
     */
    @Before
    public void setUp(Scenario scenario) {

        // Create a test entry in Extent Report for this scenario
        ExtentTestManager.createTest(scenario.getName());

        // Read browser value from config.properties
        String browser = ConfigReader.getValue("browser");

        // 🧪 QUICK CONFIRMATION (TEMPORARY – FOR DEBUGGING)
        System.out.println("BROWSER = " + browser);

        // Initialize WebDriver based on browser value
        DriverManager.initDriver(browser);

        // Launch application URL from config.properties
        DriverManager.getDriver()
                .get(ConfigReader.getValue("url"));
    }

    /**
     * Runs AFTER every scenario
     */
    @After
    public void tearDown(Scenario scenario) {

        // If scenario fails, capture screenshot and attach to report
        if (scenario.isFailed()) {

            String screenshotPath =
                    ScreenshotUtil.takeScreenshot(scenario.getName());

            ExtentTestManager.getTest()
                    .fail("Scenario Failed")
                    .addScreenCaptureFromPath(screenshotPath);

        } else {
            // Mark scenario as passed in Extent Report
            ExtentTestManager.getTest().pass("Scenario Passed");
        }

        // Quit browser and clean ThreadLocal driver
        DriverManager.quitDriver();
    }
}
