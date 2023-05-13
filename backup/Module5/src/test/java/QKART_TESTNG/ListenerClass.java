package QKART_TESTNG;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass extends QKART_Tests implements ITestListener {


    public void onTestStart(ITestResult result) {
        takeScreenshot(driver, "StartTestCase", result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        takeScreenshot(driver, "EndTestCase", result.getName());
    }

    public void onTestFailure(ITestResult result) {
        takeScreenshot(driver, "TestCaseFailed", result.getName());
    }

}