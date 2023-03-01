package org.API.Initialization;

import com.aventstack.extentreports.ExtentTest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;

import java.io.File;
import java.util.Properties;


public class GlobalVariables {

    private static ExtentTest test;

    private static String jsonPayload;
    private static File file;

    public static void setObjExtentTest(ExtentTest objExtentTest) {
        GlobalVariables.test = objExtentTest;
    }
    public static ExtentTest getObjExtentTest() {
        return test;
    }

    public static void setJsonPayload(String jsonString) {
        GlobalVariables.jsonPayload = jsonString;
    }
    public static String getJsonPayload() {
        return jsonPayload;
    }
    public static void setPayloadFilePath(File File) {
        GlobalVariables.file = File;
    }
    public static File getPayloadFilePath() {
        return file;
    }
}
