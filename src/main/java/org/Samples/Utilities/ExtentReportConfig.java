package org.Samples.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReportConfig {
    String timestamp = WrapperFunctions.getTimeStamp("HH_mm_ss");
    public ExtentReports extent;

        public static ExtentTest test;
    public ExtentReports configExtentReport(){
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\Reports\\report"+timestamp+".html");
       spark.config().setDocumentTitle("API Testing"); // set report properties
        extent.attachReporter(spark);
        return extent;
    }
}
