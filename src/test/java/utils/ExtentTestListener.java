package utils;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentTestListener implements ITestListener {

    private ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {

        String testName = result.getTestClass().getName()
                + " - "
                + result.getMethod().getMethodName();

        String description = result.getMethod().getDescription();

        test = ExtentReportManager
                .getReportInstance()
                .createTest(testName, description);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());

        WebDriver driver = DriverFactory.getDriver();

        if (driver != null) {

            String screenshot = ScreenshotUtil.captureScreenshot(
                    driver,
                    result.getMethod().getMethodName()
            );

            test.addScreenCaptureFromBase64String(screenshot);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip("Test Skipped");
    }
}