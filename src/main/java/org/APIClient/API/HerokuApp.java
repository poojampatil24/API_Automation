package org.APIClient.API;

import com.aventstack.extentreports.Status;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.APIClient.BaseConfiguration.TestConfig;

import static io.restassured.RestAssured.*;

import static org.APIClient.Listener.TestListener.test;
import static org.hamcrest.Matchers.containsString;

public class HerokuApp {

    //Authenicate API using basic authentication scheme
    public static Response authAPI(String resource,String id, String pass){
        try {
            Response response =
                    given().
                            auth().preemptive().basic(id, pass).
                            contentType(ContentType.JSON).
                    when().
                            post(resource).
                    then().
                            assertThat().statusCode(200).extract().response();
            TestConfig.logger.info("User Authenticated successfully");
            test.log(Status.PASS, "User Authenticated successfully");
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Get all the bookings
    public static Response getAllBookings(String resource) {
        try {
            Response response =
                    when().
                            get(resource).
                    then().
                            assertThat().
                            statusCode(200).
                            extract().response();
            TestConfig.logger.info("Fetched All Bookings");
            test.log(Status.PASS, "Fetched All Bookings");
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Get booking details of specific bookingId
    public static Response getInfoForSpecificBookingId(String resource ,String bookingId){
        try {
            Response response =
                    when().
                            get(resource + "/" + bookingId).
                    then().
                            assertThat().statusCode(200)
                            .extract().response();
            TestConfig.logger.info("Details of booingId:" + bookingId + " is:");
            TestConfig.logger.info(response.asString());
            test.log(Status.PASS,"Details of booingId:" + bookingId + " is:"+response.asString());
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Add new booking
    public static Response addNewBooking(String resource, String jsonString){
       try {
          Response response =
                  given()
                       .contentType(ContentType.JSON)
                       .body(jsonString).
                  when().
                          post(resource).
                  then().
                          assertThat().statusCode(200).extract().response();
           TestConfig.logger.info("Booking added successfully");
        test.log(Status.PASS,"Booking added successfully");
//           Assert.assertEquals(response.getStatusCode(), 200);
           return response;
       }catch (Exception e){
           e.printStackTrace();
       }
        return null;
    }

    //Update existing booking
//    public static Response updateBooking(File filePath, String bookingId,String resource, String firstname, String lastname, int totalprice, boolean depositpaid, String checkin, String checkout, String additionalneeds){
//        try {
//            Response response =
//                    given()
//                            .contentType(ContentType.JSON)
//                            .body(WrapperFunctions.getJsonPayload(filePath,firstname,lastname,totalprice,depositpaid,checkin,checkout,additionalneeds)).
//                    when().
//                            put("/"+resource+"/"+bookingId).
//                    then().
//                            assertThat().statusCode(200)
//                            .extract().response();
//            TestConfig.logger.error("Booking updated successfully");
//            test.log(Status.PASS,"Booking updated successfully");
//            return response;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

    //Get booking Id of a newly created booking
    public static String getBookingId(Response response){
        try {
            JsonPath jsonPath = response.jsonPath();
            String id = jsonPath.getString("bookingid");
            TestConfig.logger.info("Booking id is:" + id);
            test.log(Status.PASS, "Booking id is:" + id);
            return id;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //Get each detail value of a specific bookingId
    public static String getBookingId_KeyValue(Response response,String bookingId,String key){
        try {
            JsonPath jsonPath = response.jsonPath();
            String keyValue = jsonPath.getString("booking." + key + "");
            TestConfig.logger.info("" + key + " for " + bookingId + " is:" + keyValue);
            test.log(Status.PASS, "" + key + " for " + bookingId + " is:" + keyValue);
            return keyValue;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    //Verify Expected details and actual details are same
    public static void verifyBooking(String bookingid,String key,String keyValue) {
        try {
            when().
                    get("/booking/{bookingid}", bookingid).
                    then().
                    statusCode(200).
                    body(key, containsString(keyValue));
            TestConfig.logger.info("verified that "+key+" is as expected :"+keyValue);
            test.log(Status.PASS,"verified that "+key+" is as expected :"+keyValue);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    //Get booking by passing Query String
    public static String getBookingByQuery(String url, String resource,String queryParam){
        String response =
                when().
                        get(resource+"?"+queryParam).
                        then().
                        assertThat().statusCode(200)
                        .log().body().extract().response().asString();
        return response;
    }

}
