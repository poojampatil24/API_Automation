package org.Samples.Utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javafaker.Faker;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class CommonFunctions {

    public static Faker faker = new Faker();;
    public String getRandomFirstName() {
        String firstName = faker.name().firstName();
        return firstName;
    }
    public String getRandomLastName() {
        String lastName = faker.name().lastName();
        return lastName;
    }
    public int getRandomTotalPrice() {
        int totalPrice = Integer.parseInt(faker.number().digits(3));
        return totalPrice;
    }
    public boolean getRandomDepositPaid() {
        boolean depositPaid = faker.bool().bool();
        return depositPaid;
    }

    public String getRandomDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(faker.date().birthday());
        return date;
    }
    public String getRandomAdditionalNeeds() {
        String additionalNeeds = faker.food().dish();
        return additionalNeeds;
    }
    public File getJsonFile(String fileName){
        File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\org\\Samples\\JsonPayload\\"+fileName+".json");
        return file;
    }
    //Get Json Payload
    public String getJsonPayload(File filePath, String firstnameKey,String firstName,String lastnameKey, String lastName, String totalpriceKey,int totalPrice,String depositpaidKey, boolean depositPaid,String bookingdatesLabel,String checkinKey, String checkInDate,String checkoutKey ,String checkOutDate, String additionalneedsKey,String additionalNeeds) throws IOException {
        String jsonString = WrapperFunctions.readJsonFile(filePath);

        // Deserialize the JSON string into a JsonNode
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode bookingdataNode = objectMapper.readTree(jsonString);

        // Modify the JsonNode
        ((ObjectNode) bookingdataNode).put(firstnameKey, firstName);
        ((ObjectNode) bookingdataNode).put(lastnameKey, lastName);
        ((ObjectNode) bookingdataNode).put(totalpriceKey, totalPrice);
        ((ObjectNode) bookingdataNode).put(depositpaidKey, depositPaid);
        ObjectNode bookingdatesNode = ((ObjectNode) bookingdataNode).putObject(bookingdatesLabel);
        bookingdatesNode.put(checkinKey, checkInDate);
        bookingdatesNode.put(checkoutKey, checkOutDate);
        ((ObjectNode) bookingdataNode).put(additionalneedsKey, additionalNeeds);


        // Serialize back to JSON
        String newJsonString = objectMapper.writeValueAsString(bookingdataNode);
        System.out.println("New Json:"+newJsonString);
        return newJsonString;
    }

}
