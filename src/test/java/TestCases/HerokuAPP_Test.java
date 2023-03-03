package TestCases;

import io.restassured.response.Response;
import org.Samples.Utilities.CommonFunctions;
import org.Samples.Utilities.ExcelDataConfig;
import org.Samples.BaseConfiguration.BaseConfig;
import org.Samples.API.HerokuApp;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class HerokuAPP_Test extends BaseConfig {

    public File jsonFile;
    public HashMap<String, String> authData;
    public HashMap<String, String> endPointData;
    public HashMap<String, String> testData;
    @Test
    public void TestCase1() throws IOException{

        CommonFunctions commonFunctions = new CommonFunctions();
        ExcelDataConfig excelDataConfig = new ExcelDataConfig();

        /******** Random data generator to create a request body ********/
        String firstName = commonFunctions.getRandomFirstName();
        String lastName = commonFunctions.getRandomLastName();
        int totalPrice = commonFunctions.getRandomTotalPrice();
        boolean depositPaid = commonFunctions.getRandomDepositPaid();
        String checkin = commonFunctions.getRandomDate();
        String checkout =  commonFunctions.getRandomDate();
        String additionalNeeds = commonFunctions.getRandomAdditionalNeeds();


        /******** Data Files ********/
        jsonFile = commonFunctions.getJsonFile("BookingPayload");
        authData = excelDataConfig.getData("BaseData", "BasicConfigData");
        endPointData = excelDataConfig.getData("BaseData", "EndPointData");
        testData = excelDataConfig.getData("TestData", "HerokuApp_Test1");


        /******** Get Booking Json Payload *********/
        String jsonString = commonFunctions.getJsonPayload(jsonFile,testData.get("firstnameLabel"),firstName,testData.get("lastnameLabel"),lastName,testData.get("totalpriceLabel"),totalPrice,testData.get("depositpaidLabel"),depositPaid,testData.get("bookingdatesLabel"),testData.get("checkinLabel"),checkin,testData.get("checkoutLabel"),checkout,testData.get("additionalneedsLabel"),additionalNeeds);


        /******** Start Test Case *********/
        HerokuApp.authAPI(endPointData.get("authEndPoint"), authData.get("username"), authData.get("password"));
        Response response = HerokuApp.addNewBooking(endPointData.get("bookingEndPoint"), jsonString);
        String bookingId = HerokuApp.getBookingId(response);
        HerokuApp.getInfoForSpecificBookingId(endPointData.get("bookingEndPoint"),bookingId);
        String firstname = HerokuApp.getBookingId_KeyValue(response,bookingId,testData.get("firstnameLabel"));
        HerokuApp.verifyBooking(bookingId,testData.get("firstnameLabel"),firstname);
        /******** End Test Case *********/

    }
}
