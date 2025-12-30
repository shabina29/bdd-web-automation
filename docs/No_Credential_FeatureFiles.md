✅ COMMENTS TO ADD DIRECTLY IN FEATURE FILE

Paste this at the top of your feature file 👇

# =========================
# WHY WE DO NOT PASS CREDENTIALS IN FEATURE FILES
# =========================
# 1. Feature files are written for business readability, not test data handling.
#    Keeping credentials here exposes sensitive information.
#
# 2. Credentials can change frequently (env-wise or user-wise).
#    Keeping them in code/config avoids frequent feature file changes.
#
# 3. Feature files should describe WHAT the user does,
#    not HOW the data is managed internally.
#
# 4. Storing credentials in step definitions or config files:
#    - Improves security
#    - Improves reusability
#    - Avoids duplication
#
# 5. In real projects, credentials usually come from:
#    - config.properties
#    - environment variables
#    - test data files
#
# Best Practice:
# Feature File → Business Flow
# Step Definition → Test Logic
# Config/Test Data → Credentials
# =========================

✅ HOW YOUR FEATURE FILE SHOULD LOOK (WITH THIS PRACTICE)
@Regression
Feature: SauceDemo Login

  @Smoke
  Scenario: Valid user should login successfully
    Given user is on SauceDemo login page
    When user enters valid username and password
    And clicks on login button
    Then Products page should be displayed


✔ Clean
✔ Secure
✔ Reusable
✔ Interview-safe

🧠 INTERVIEW ANSWER (MEMORISE THIS)

If interviewer asks:

❓ Why don’t you pass credentials in feature files?

👉 Say this:

“Feature files are meant for business readability.
I keep credentials outside feature files to improve security, reusability, and maintainability, and manage them through config files or test data sources.”

🔥 This is a senior automation answer.

❌ WHAT INTERVIEWERS DON’T LIKE
When user logs in with username "admin" and password "admin123"


❌ Security risk
❌ Hard-coded data
❌ Poor design

🎯 FINAL RULE (VERY IMPORTANT)

Feature files = WHAT
Step definitions = HOW
Config/Test data = DATA

If you remember just this line, you’re safe in interviews.

If you want next, I can add comments for:
1️⃣ Why we use tags (@Smoke, @Regression, @Negative)
2️⃣ Why assertions are not written in Page classes
3️⃣ End-to-end interview explanation of your SauceDemo project