package org.APIClient.BaseConfiguration;

import com.aventstack.extentreports.ExtentTest;
import org.APIClient.Utilities.ExcelDataConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;

import java.io.*;
import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class TestConfig {

    private static ExtentTest test;
    public static Logger logger = LogManager.getLogger(TestConfig.class);

    @BeforeTest
    public void configHerokuApp() throws IOException {
        HashMap<String, String> endPointData = ExcelDataConfig.getData("BaseData", "BasicConfigData");
        baseURI = endPointData.get("baseUri");
    }
    public static void setObjExtentTest(ExtentTest objExtentTest) {
        TestConfig.test = objExtentTest;
    }
}
