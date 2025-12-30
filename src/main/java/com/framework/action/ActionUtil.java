package com.framework.action;

import com.framework.manager.DriverManager;
import com.framework.utils.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ActionUtil {

    private static final int TIMEOUT =
            Integer.parseInt(ConfigReader.getValue("timeout"));

    private static WebDriver driver() {
        return DriverManager.getDriver();
    }

    private static WebDriverWait getWait() {
        return new WebDriverWait(driver(), Duration.ofSeconds(TIMEOUT));
    }

    // ---------- WAITS ----------
    public static void waitForVisible(By locator) {
        getWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForClickable(By locator) {
        getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }

    // ---------- ACTIONS ----------
    public static void type(By locator, String value) {
        waitForVisible(locator);
        WebElement element = driver().findElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    public static void click(By locator) {
        waitForClickable(locator);
        driver().findElement(locator).click();
    }

    public static boolean isDisplayed(By locator) {
        try {
            waitForVisible(locator);
            return driver().findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static String getText(By locator) {
        waitForVisible(locator);
        return driver().findElement(locator).getText();
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