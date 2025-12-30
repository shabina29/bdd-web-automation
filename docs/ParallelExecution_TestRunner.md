🟢 BASIC LEVEL
❓ 1. What is parallel execution?

Answer:

“Parallel execution means running multiple test cases at the same time instead of sequentially, which reduces overall execution time.”

❓ 2. Why do we need parallel execution?

Answer:

“Parallel execution helps reduce regression execution time and makes the framework scalable for CI/CD pipelines.”

❓ 3. Can Selenium support parallel execution?

Answer:

“Yes, Selenium supports parallel execution when combined with TestNG, ThreadLocal, or Selenium Grid.”

❓ 4. Which tool controls parallel execution in your framework?

Answer:

“Parallel execution is controlled by TestNG using a DataProvider configured with parallel=true.”

🟡 INTERMEDIATE LEVEL
❓ 5. How did you enable parallel execution in your framework?

Answer:

“We enabled parallel execution by overriding the scenarios() method in the Cucumber TestNG runner and using a parallel DataProvider.”

❓ 6. What runs in parallel in your framework?

Answer:

“Each Cucumber scenario runs in a separate thread.”

❓ 7. How many browsers open during parallel execution?

Answer:

“One browser instance opens per scenario thread.”

❓ 8. How do you prevent driver collision?

Answer:

“We use ThreadLocal in DriverManager so each thread has its own WebDriver instance.”

❓ 9. What happens if you don’t use ThreadLocal?

Answer:

“Without ThreadLocal, multiple tests share the same driver, causing session override and flaky failures.”

❓ 10. Is parallel execution enabled by default?

Answer:

“No, it must be explicitly enabled in the TestNG runner.”

🔵 ADVANCED LEVEL
❓ 11. Explain ThreadLocal in simple terms.

Answer:

“ThreadLocal provides a separate copy of a variable for each thread, ensuring isolation during parallel execution.”

❓ 12. Where exactly do you use ThreadLocal?

Answer:

“ThreadLocal is used in DriverManager for WebDriver and in ExtentTestManager for ExtentTest logging.”

❓ 13. How do you handle reporting in parallel execution?

Answer:

“We store ExtentTest instances in ThreadLocal so logs from different scenarios don’t mix.”

❓ 14. How do Hooks behave in parallel execution?

Answer:

“Hooks run independently for each scenario thread, so browser setup and cleanup happen per scenario.”

❓ 15. How do you debug parallel execution issues?

Answer:

“We add thread ID logging, analyze report timestamps, and verify ThreadLocal usage.”

🔴 VERY ADVANCED / FOLLOW-UP QUESTIONS
❓ 16. What are common issues in parallel execution?

Answer:

“Driver collision, shared static variables, report log mixing, and unstable application state.”

❓ 17. How do you make a framework parallel-safe?

Answer:

“By avoiding shared static state, using ThreadLocal, and cleaning up drivers after every scenario.”

❓ 18. Can page objects be shared across threads?

Answer:

“Yes, as long as they do not store WebDriver as an instance variable and always fetch it via DriverManager.”

❓ 19. Can parallel execution increase flaky tests?

Answer:

“Yes, if thread safety is not handled properly. When designed correctly, parallel execution actually improves reliability.”

❓ 20. How does parallel execution help CI/CD?

Answer:

“It reduces pipeline execution time and enables faster feedback for releases.”

⭐ EXPERT-LEVEL QUESTIONS
❓ 21. How would you scale parallel execution further?

Answer:

“By using Selenium Grid or cloud services like BrowserStack along with ThreadLocal-based framework design.”

❓ 22. Why not use synchronized blocks instead of ThreadLocal?

Answer:

“Synchronized blocks reduce concurrency, while ThreadLocal provides isolation without blocking.”

❓ 23. What happens if one parallel test fails?

Answer:

“Failure of one scenario does not affect other threads; execution continues independently.”

❓ 24. Can you run features in parallel?

Answer:

“Yes, depending on runner configuration, scenarios or features can be executed in parallel.”

🎤 30-SECOND MASTER ANSWER (MEMORISE THIS)

“Parallel execution is enabled using TestNG DataProvider.
Each scenario runs in a separate thread, and ThreadLocal ensures driver and report isolation.
Hooks manage setup and cleanup per scenario, making execution fast, safe, and CI-ready.”

🧠 MEMORY TRICK

Parallel = Speed + ThreadLocal + Isolatio