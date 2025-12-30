package com.framework.utils;

import com.aventstack.extentreports.ExtentTest;

/**
 * ExtentTestManager
 * -----------------
 * Purpose:
 * Manages ExtentTest instances in a thread-safe way.
 *
 * Why ThreadLocal?
 * - Required for parallel execution
 * - Each scenario gets its own report entry
 */
public class ExtentTestManager {

    // Thread-safe ExtentTest storage
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    /**
     * Creates a test entry in Extent Report
     */
    public static void createTest(String testName) {
        ExtentTest test = ExtentManager.getExtent().createTest(testName);
        extentTest.set(test);
    }

    /**
     * Returns the current ExtentTest
     */
    public static ExtentTest getTest() {
        return extentTest.get();
    }
}
