package PracticeTestCases;

import io.restassured.response.Response;
import org.API.Initialization.ExcelDataConfig;
import org.API.Initialization.GlobalVariables;
import org.API.Initialization.TestConfig;
import org.API.API.HerokuApp;
import org.API.Utilities.WrapperFunctions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class HerokuAPP_Test extends TestConfig {

    @Test
    public void TestCase1() throws IOException, InterruptedException {


        HashMap<String, String> authData = ExcelDataConfig.getData("BaseData", "AuthenticationData");
        HashMap<String, String> endPointData = ExcelDataConfig.getData("BaseData", "EndPointData");
        HashMap<String, String> testData = ExcelDataConfig.getData("TestData", "HerokuApp_Test1");
        File filePath = GlobalVariables.getPayloadFilePath();
        String jsonString = GlobalVariables.getJsonPayload();


        HerokuApp.authAPI(endPointData.get("authEndPoint"), authData.get("username"), authData.get("password"));
        Response response = HerokuApp.addNewBooking(endPointData.get("bookingEndPoint"), jsonString);
        String bookingId = HerokuApp.getBookingId(response);
        HerokuApp.getInfoForSpecificBookingId(endPointData.get("bookingEndPoint"),bookingId);

//        String firstname = HerokuApp.getBookingId_KeyValue(response,bookingId,testData.get("firstname"));
//        HerokuApp.verifyBooking(bookingId,testData.get("firstnameLabel"),firstname);
//        Thread.sleep(5000);
//        Response response1 =  HerokuApp.updateBooking(filePath,"booking",bookingId,"Pooja","Bansal",300,true,"2023-01-01","2023-01-02","need extra pillows");

    }

}
