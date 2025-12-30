package com.framework.utils;

import com.framework.manager.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ScreenshotUtil
 * --------------
 * Responsibility:
 * Handles screenshot capture logic for failed scenarios.
 *
 * Why this class exists:
 * - Keeps screenshot logic separate from test logic
 * - Follows Single Responsibility Principle
 * - Makes framework clean and maintainable
 */
public class ScreenshotUtil {

    /**
     * takeScreenshot()
     * ----------------
     * Captures screenshot and saves it under test-output/screenshots
     *
     * @param scenarioName Name of the failed scenario
     * @return Absolute path of saved screenshot
     */
    public static String takeScreenshot(String scenarioName) {

        // Convert WebDriver to TakesScreenshot
        File srcFile = ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.FILE);

        // Clean scenario name to avoid invalid file characters
        String cleanName = scenarioName.replaceAll("[^a-zA-Z0-9]", "_");

        // Add timestamp to avoid overwrite issues
        String timestamp = String.valueOf(System.currentTimeMillis());

        // Create screenshots directory if not exists
        Path destDir = Paths.get("test-output", "screenshots");
        try {
            Files.createDirectories(destDir);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create screenshot directory", e);
        }

        // Final screenshot path (unique name)
        Path destPath =
                destDir.resolve(cleanName + "_" + timestamp + ".png");

        // Copy screenshot file
        try {
            Files.copy(srcFile.toPath(), destPath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot", e);
        }

        return destPath.toAbsolutePath().toString();
    }
}

