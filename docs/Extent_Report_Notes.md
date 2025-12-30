## Why do we use ExtentManager and ExtentTestManager?

In our framework, reporting is split into two responsibilities:
- Report creation and configuration
- Test-level logging

To keep the framework clean, scalable, and parallel-execution safe, we separate these responsibilities into two classes: **ExtentManager** and **ExtentTestManager**.

---

### 1️⃣ ExtentManager – Report Setup & Configuration

**Purpose:**
ExtentManager is responsible for creating and configuring the main Extent Report.

**Why we need ExtentManager:**
- To create **only one ExtentReports instance** for the entire test run
- To avoid duplicate or overwritten report files
- To centralize report configuration (report name, title, system info)
- To make report creation reusable and maintainable

**What ExtentManager handles:**
- Report file path
- Report theme and configuration
- System/environment information
- Returning a singleton ExtentReports object

👉 ExtentManager answers the question:  
**“Where and how is the report created?”**

---

### 2️⃣ ExtentTestManager – Scenario-Level Logging

**Purpose:**
ExtentTestManager is responsible for creating and managing individual test logs inside the report.

**Why we need ExtentTestManager:**
- Each scenario needs its **own log entry** in the report
- In parallel execution, logs must not mix between tests
- Step-level logging should be isolated per scenario

**What ExtentTestManager handles:**
- Creating a test node for each scenario
- Storing ExtentTest object using ThreadLocal
- Providing the correct test instance for logging
- Flushing the report after execution

👉 ExtentTestManager answers the question:  
**“Which test is currently logging?”**

---

### 3️⃣ Why not use ExtentReports directly everywhere?

Using ExtentReports directly in Hooks, steps, or utilities would:
- Mix report setup and test logging responsibilities
- Cause log overlap in parallel execution
- Make the framework hard to maintain
- Increase chances of flaky or corrupted reports

Separating managers ensures:
- Clean architecture
- Thread-safe execution
- Easy debugging and maintenance

---

### 4️⃣ How ExtentManager and ExtentTestManager work together

**Flow:**
1. ExtentManager creates the report (once per execution)
2. ExtentTestManager creates a test entry for each scenario
3. Hooks and utilities log pass/fail and screenshots
4. ExtentTestManager flushes the report at the end

---

### 🎤 Interview Answer (Main)

> “ExtentManager is responsible for creating and configuring the report, while ExtentTestManager handles scenario-level logging.  
This separation avoids report duplication, supports parallel execution using ThreadLocal, and keeps reporting logic clean and maintainable.”

---

### ⭐ One-Line Strong Answer

> “ExtentManager creates the report, ExtentTestManager manages per-scenario logs.”

---

### 🧠 Memory Trick

**ExtentManager = Report creation**  
**ExtentTestManager = Test logging**

---

### 📌 How this helps the framework
- Clean separation of responsibilities
- Parallel execution safe
- No report corruption
- Easier maintenance
- Interview-ready design
