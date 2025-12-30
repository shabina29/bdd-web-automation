package com.framework.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.framework.steps",
                "com.framework.base"
        },
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json"
        },
        monochrome = true,
        tags = "@E2E"   // 🔥 IMPORTANT: Run ONLY E2E scenarios
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
