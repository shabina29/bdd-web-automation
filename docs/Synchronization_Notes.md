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
