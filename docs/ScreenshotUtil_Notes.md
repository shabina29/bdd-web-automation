## 📌 Screenshot Handling in Automation Framework

### ❓ Why do we capture screenshots?
- Screenshots help in debugging test failures  
- Very useful in CI/CD pipelines  
- Improves test execution reports  
- Helps developers and QA quickly understand UI issues  

---

### ❓ When do we capture screenshots?
- Screenshots are captured **only when a scenario fails**  
- Triggered from **Cucumber `@After` Hook**  
- Avoids unnecessary screenshots for passed tests  

---

### ❓ Where is screenshot logic implemented?
- Screenshot logic is kept inside a **separate utility class**  
- Class name: `ScreenshotUtil`  
- Package: `com.framework.utils`  

👉 This follows the **Single Responsibility Principle**

---

### 📌 Important Code Line (Frequently Asked in Interview)

```java
File src = ((TakesScreenshot) DriverManager.getDriver())
                .getScreenshotAs(OutputType.FILE);
Explanation:
Selenium provides screenshot capability through the TakesScreenshot interface

WebDriver is typecast to TakesScreenshot

getScreenshotAs(OutputType.FILE) captures the screenshot as an image file

The file is later saved and attached to the report

❓ Why typecasting is required?
WebDriver interface does not expose screenshot methods directly

Screenshot functionality belongs to TakesScreenshot

Hence, explicit typecasting is required

❓ Why OutputType.FILE?
Saves screenshot as a physical image file

Easy to attach to Extent Reports

Best option for reporting and debugging

Other options:

BASE64 → API / embedded usage

BYTES → In-memory usage

❓ How is screenshot path handled?
Screenshots are stored under:

bash
Copy code
test-output/screenshots/
Scenario name is sanitized to avoid invalid file names

Directories are created dynamically to avoid runtime failures in CI

❓ How are screenshots attached to reports?
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

📌 How does this help avoid flaky tests?
No manual screenshots

No test interruption

Failure evidence is always available

Cleaner CI execution and faster debugging

yaml
Copy code

---

## ✅ WHY THIS IS PERFECT NOW

✔ Clean Markdown  
✔ Interview-friendly  
✔ Easy to revise before interviews  
✔ Professional documentation style  
✔ Safe to push to GitHub  

---

### NEXT (Your Choice)
Reply with:
- **HOOKS NOTES** → Before/After hook interview notes  
- **REPORT NOTES** → Extent Report deep dive  
- **PARALLEL NOTES** → ThreadLocal & parallel execution  
- **CHEAT SHEET** → 1-page final interview summary  