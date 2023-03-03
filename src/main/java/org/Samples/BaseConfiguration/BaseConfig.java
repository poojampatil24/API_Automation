package org.Samples.BaseConfiguration;

import com.aventstack.extentreports.ExtentTest;
import org.Samples.Utilities.ExcelDataConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;

import java.io.*;
import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class BaseConfig {

    private static ExtentTest test;
    public static Logger logger = LogManager.getLogger(BaseConfig.class);

    @BeforeTest
    public void configHerokuApp() throws IOException {

        ExcelDataConfig excelDataConfig = new ExcelDataConfig();

        /******* BaseUri *******/
        HashMap<String, String> endPointData = excelDataConfig.getData("BaseData", "BasicConfigData");
        baseURI = endPointData.get("baseUri");
    }
    public static void setObjExtentTest(ExtentTest objExtentTest) {
        BaseConfig.test = objExtentTest;
    }
}
