🎯 WHY THIS TAG STRATEGY IS CORRECT
🟢 @Smoke

Fast

Critical functionality

Runs on every build

👉 Flights menu = core business flow → Smoke

🔵 @Regression

Full coverage

Runs nightly or before release

👉 All menu validations → Regression

▶️ HOW TO EXECUTE
Smoke only
mvn clean test -Psmoke

Regression only
mvn clean test -Pregression

🎤 INTERVIEW ANSWER (VERY IMPORTANT)

“We tag scenarios in feature files using @Smoke and @Regression.
Smoke covers critical flows and runs frequently, while Regression covers complete validation and runs less often.
Execution is controlled using Maven profiles.”

🧠 MEMORY TRICK

Feature = grouping
Scenario tags = execution control