package org.API.Initialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.API.Utilities.WrapperFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.*;
import java.text.SimpleDateFormat;

import static io.restassured.RestAssured.*;

public class TestConfig extends GlobalVariables{


    public static Logger logger = LogManager.getLogger(TestConfig.class);

    @BeforeSuite
    public void configHerokuApp() throws IOException {

        baseURI = "https://restful-booker.herokuapp.com";

        File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\org\\API\\HerokuAppPayloads\\Booking.json");

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        int totalPrice = Integer.parseInt(faker.number().digits(3));
        boolean depositPaid = faker.bool().bool();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String checkin = dateFormat.format(faker.date().birthday());
        String checkout = dateFormat.format(faker.date().birthday());
        String additionalNeeds = faker.food().dish();

        String jsonString = WrapperFunctions.getJsonPayload(file,firstName,lastName,totalPrice,depositPaid,checkin,checkout,additionalNeeds);
        GlobalVariables.setJsonPayload(jsonString);
        GlobalVariables.setPayloadFilePath(file);

    }
}
