package org.Samples.Listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.Samples.BaseConfiguration.BaseConfig;
import org.Samples.Utilities.ExtentReportConfig;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends BaseConfig implements ITestListener{
    ExtentReportConfig extentReportConfig = new ExtentReportConfig();

    public ExtentReports extent = extentReportConfig.configExtentReport();

    public static ExtentTest test;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        test.log(Status.PASS,"Test started");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        test.log(Status.PASS,"Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        test.log(Status.FAIL,iTestResult.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        test.log(Status.SKIP,iTestResult.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        test.log(Status.WARNING,iTestResult.getThrowable());
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        test = extent.createTest(iTestContext.getName());
        BaseConfig.setObjExtentTest(test);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        test.log(Status.PASS,"Test Completed");
        extent.flush();
    }
}
