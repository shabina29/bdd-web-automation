package com.framework.action;

import com.framework.manager.DriverManager;
import com.framework.utils.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionUtil {

    /*
     * Timeout value is externalized in config.properties
     * This avoids hardcoding and helps tune waits per environment
     */
    private static final int TIMEOUT =
            Integer.parseInt(ConfigReader.getValue("timeout"));

    /*
     * Fetch WebDriver from DriverManager (ThreadLocal based)
     * Ensures thread safety during parallel execution
     */
    private static WebDriver driver() {
        return DriverManager.getDriver();
    }

    /*
     * Centralized Explicit Wait creator
     * Explicit waits are preferred over Thread.sleep & Implicit wait
     */
    private static WebDriverWait getWait(int seconds) {
        return new WebDriverWait(driver(), Duration.ofSeconds(seconds));
    }

    /*
     * Wait until element becomes visible
     */
    private static void waitForVisible(By locator, int seconds) {
        getWait(seconds)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /*
     * Wait until element becomes clickable
     */
    private static void waitForClickable(By locator, int seconds) {
        getWait(seconds)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    /*
     * CLICK ACTION (MOST IMPORTANT)
     * ------------------------------
     * 1. Waits until element is clickable
     * 2. Tries normal Selenium click
     * 3. Falls back to JavaScript click if intercepted
     *
     * This design removes flaky click failures on dynamic sites
     * like Booking.com (overlays, banners, animations).
     */
    public static void click(By locator) {
        try {
            waitForClickable(locator, TIMEOUT);
            driver().findElement(locator).click();
        } catch (Exception e) {
            // JS fallback for intercepted / overlay issues
            JavascriptExecutor js = (JavascriptExecutor) driver();
            js.executeScript(
                    "arguments[0].click();",
                    driver().findElement(locator)
            );
        }
    }

    /*
     * TYPE ACTION
     * Clears existing value and sends new keys
     */
    public static void type(By locator, String value) {
        waitForVisible(locator, TIMEOUT);
        WebElement element = driver().findElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    /*
     * GET TEXT ACTION
     * Ensures element is visible before reading text
     */
    public static String getText(By locator) {
        waitForVisible(locator, TIMEOUT);
        return driver().findElement(locator).getText();
    }

    /*
     * IS DISPLAYED CHECK
     * Used for validations
     */
    public static boolean isDisplayed(By locator) {
        waitForVisible(locator, TIMEOUT);
        return driver().findElement(locator).isDisplayed();
    }

    /*
     * Booking.com Genius popup (appears randomly)
     */
    private static final By geniusPopupClose =
            By.xpath("//button[@aria-label='Dismiss sign-in info.']");

    /*
     * Safely handles optional popup
     * Test will not fail if popup is absent
     */
    public static void handleGeniusPopupIfPresent() {
        try {
            if (driver().findElements(geniusPopupClose).size() > 0) {
                driver().findElement(geniusPopupClose).click();
            }
        } catch (Exception ignored) {
            // Popup may not appear – ignore safely
        }
    }

    /*
     * Switches control to newly opened browser tab
     */
    public static void switchToNewTab() {
        String parentWindow = driver().getWindowHandle();

        for (String window : driver().getWindowHandles()) {
            if (!window.equals(parentWindow)) {
                driver().switchTo().window(window);
                break;
            }
        }
    }
}

/*🎤 HOW TO EXPLAIN THIS IN INTERVIEW (1 LINE)

“We avoid flaky tests by centralizing all waits and actions inside ActionUtil, using explicit waits with configurable timeout, handling optional popups safely, and keeping WebDriver thread-safe.”

🧠 MEMORY LINE (VERY IMPORTANT)

ActionUtil = Centralized actions + Explicit waits + Config-driven timeout + Flaky-test control*/


/*🎤 Interview Question: How do you avoid flaky tests?
✅ Best Answer (Real, Professional, Spoken Style)

“In my project, we avoid flaky tests mainly through proper synchronization and clean framework design.
We use explicit waits instead of Thread.sleep and centralize all waits inside a common ActionUtil class.
Locators are kept stable by using reliable attributes instead of dynamic ones.
WebDriver lifecycle is managed through a DriverManager with ThreadLocal support, which avoids issues in parallel execution.
We also handle optional popups safely and clean up the browser after every scenario using Hooks.”

🧠 Breakdown (So You Truly Understand It)
🔹 1️⃣ Proper Synchronization (MOST IMPORTANT)

“We use explicit waits like visibility and clickability before every action.”

✔ No Thread.sleep
✔ No race conditions
✔ Condition-based waiting

🔹 2️⃣ Centralized Wait Logic

“All waits are centralized inside ActionUtil instead of being scattered across tests.”

✔ Single place to fix issues
✔ Easy maintenance
✔ Consistent behavior

🔹 3️⃣ Stable Locators

“We avoid dynamic IDs and use reliable attributes like name, text, aria-label, or relative XPath.”

✔ Fewer locator failures
✔ More resilient tests

🔹 4️⃣ Thread-safe Driver Management

“DriverManager uses ThreadLocal to ensure each test thread has its own WebDriver instance.”

✔ No session clashes
✔ Safe parallel runs

🔹 5️⃣ Clean Test Lifecycle

“We use Hooks to initialize and quit the browser properly for every scenario.”

✔ No leftover sessions
✔ Clean execution

🔹 6️⃣ Safe Handling of Optional Elements

“Dynamic popups are handled with conditional checks so tests don’t fail if the popup doesn’t appear.”

✔ Real-world robustness

⭐ 1-LINE STRONG VERSION (IF THEY ASK QUICKLY)

“We avoid flaky tests by using explicit waits, stable locators, centralized action utilities, thread-safe driver management, and clean browser lifecycle handling.”

🧠 Memory Trick (VERY IMPORTANT)

Flaky tests = Timing + Locators + Driver issues
Fix = Waits + Stability + Thread safety*/