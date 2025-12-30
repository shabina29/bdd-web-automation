# Framework Interview Notes

## How do we avoid flaky tests?

In my project, we avoid flaky tests mainly through proper synchronization and clean framework design.

### 1. Proper Synchronization
We use explicit waits like visibility and clickability before every action.
- No Thread.sleep
- No race conditions
- Condition-based waiting

### 2. Centralized Wait Logic
All waits are centralized inside ActionUtil instead of being scattered across tests.
- Single place to fix issues
- Easy maintenance
- Consistent behavior

### 3. Stable Locators
We avoid dynamic IDs and use reliable attributes like name, text, aria-label, or relative XPath.

### 4. Thread-safe Driver Management
DriverManager uses ThreadLocal to ensure each test thread has its own WebDriver instance.

### 5. Clean Test Lifecycle
Hooks are used to initialize and quit the browser properly for every scenario.

### 6. Safe Handling of Optional Elements
Dynamic popups are handled with conditional checks so tests don’t fail if the popup doesn’t appear.

### One-line interview answer
We avoid flaky tests by using explicit waits, stable locators, centralized action utilities, thread-safe driver management, and clean browser lifecycle handling.


📘 Framework Interview Notes
📌 Screenshot Handling in Automation Framework
Why do we capture screenshots?

Screenshots help in debugging test failures

Very useful in CI/CD pipelines

Improves test execution reports

Helps developers and QA quickly understand UI issues

📌 When do we capture screenshots?

Screenshots are captured only when a scenario fails

Triggered from Cucumber @After Hook

Avoids unnecessary screenshots for passed tests

📌 Where is screenshot logic implemented?

Screenshot logic is kept inside a separate utility class

Class name: ScreenshotUtil

Package: com.framework.utils

👉 This follows Single Responsibility Principle

📌 Important Code Line (Frequently Asked in Interview)
File src = ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.FILE);

Explanation:

Selenium provides screenshot capability through TakesScreenshot interface

WebDriver is typecast to TakesScreenshot

getScreenshotAs(OutputType.FILE) captures the screenshot as an image file

The file is later saved and attached to the report

📌 Why typecasting is required?

WebDriver interface does not expose screenshot methods directly

Screenshot functionality belongs to TakesScreenshot

Hence, explicit typecasting is required

📌 Why OutputType.FILE?

Saves screenshot as a physical image file

Easy to attach to Extent Reports

Best option for reporting and debugging

Other options:

BASE64 → API / embedded usage

BYTES → in-memory usage

📌 How screenshot path is handled?

Screenshots are stored under:

test-output/screenshots/


Scenario name is sanitized to avoid invalid file names

Directories are created dynamically to avoid runtime failures in CI

📌 How screenshots are attached to reports?

Screenshot path is returned from ScreenshotUtil

Attached to Extent Report inside @After Hook

Makes failure analysis faster

🎤 Interview Answer (Main)

“We capture screenshots automatically on test failure using a ScreenshotUtil class.
The driver is typecast to TakesScreenshot, and the screenshot is saved as a file.
This screenshot is then attached to the Extent report for better debugging.”

⭐ One-Line Strong Answer (Quick Round)

“Screenshots are captured on failure using TakesScreenshot and attached to Extent Reports via Hooks.”

🧠 Memory Trick

Failure → Hook → ScreenshotUtil → File → Extent Report

📌 How this helps avoid flaky tests?

No manual screenshots

No test interruption

Failure evidence is always available

Cleaner CI execution and debugging
