package TestCases;

import io.restassured.response.Response;
import org.APIClient.Utilities.CommonFunctions;
import org.APIClient.Utilities.ExcelDataConfig;
import org.APIClient.BaseConfiguration.TestConfig;
import org.APIClient.API.HerokuApp;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class HerokuAPP_Test extends TestConfig {

    public File jsonFile;
    public HashMap<String, String> authData;
    public HashMap<String, String> endPointData;
    public HashMap<String, String> testData;
    @Test
    public void TestCase1() throws IOException{
        /******** Random data generator to create a request body ********/
        String firstName = CommonFunctions.getRandomFirstName();
        String lastName = CommonFunctions.getRandomLastName();
        int totalPrice = CommonFunctions.getRandomTotalPrice();
        boolean depositPaid = CommonFunctions.getRandomDepositPaid();
        String checkin = CommonFunctions.getRandomDate();
        String checkout =  CommonFunctions.getRandomDate();
        String additionalNeeds = CommonFunctions.getRandomAdditionalNeeds();


        /******** Data Files ********/
        jsonFile = CommonFunctions.getJsonFile("BookingPayload");
        authData = ExcelDataConfig.getData("BaseData", "BasicConfigData");
        endPointData = ExcelDataConfig.getData("BaseData", "EndPointData");
        testData = ExcelDataConfig.getData("TestData", "HerokuApp_Test1");


        /******** Get Booking Json Payload *********/
        String jsonString = CommonFunctions.getJsonPayload(jsonFile,testData.get("firstnameLabel"),firstName,testData.get("lastnameLabel"),lastName,testData.get("totalpriceLabel"),totalPrice,testData.get("depositpaidLabel"),depositPaid,testData.get("bookingdatesLabel"),testData.get("checkinLabel"),checkin,testData.get("checkoutLabel"),checkout,testData.get("additionalneedsLabel"),additionalNeeds);


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
