package org.API.Utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class WrapperFunctions {

    public static SimpleDateFormat formatter;
    public static Timestamp timestamp;
    public static String getTimeStamp(String timeStampFormat){
        try {
            formatter = new SimpleDateFormat(timeStampFormat);
            timestamp = new Timestamp(System.currentTimeMillis());
            String retTimeStamp = formatter.format(timestamp);
            return retTimeStamp;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static String readJsonFile(File filePath) throws FileNotFoundException {
        String jsonString;
        try (FileReader reader = new FileReader(filePath.toString())) {
            char[] buffer = new char[1024];
            int charsRead = reader.read(buffer);
            jsonString = new String(buffer, 0, charsRead);
            return jsonString;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getJsonPayload(File filePath, String firstName, String lastName, int totalPrice, boolean depositPaid, String checkInDate, String checkOutDate, String additionalNeeds) throws IOException {
        String jsonString = WrapperFunctions.readJsonFile(filePath);

        // Deserialize the JSON string into a JsonNode
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode bookingdataNode = objectMapper.readTree(jsonString);

        // Modify the JsonNode
        ((ObjectNode) bookingdataNode).put("firstname", firstName);
        ((ObjectNode) bookingdataNode).put("lastname", lastName);
        ((ObjectNode) bookingdataNode).put("totalprice", totalPrice);
        ((ObjectNode) bookingdataNode).put("depositpaid", depositPaid);
        ObjectNode bookingdatesNode = ((ObjectNode) bookingdataNode).putObject("bookingdates");
        bookingdatesNode.put("checkin", checkInDate);
        bookingdatesNode.put("checkout", checkOutDate);
        ((ObjectNode) bookingdataNode).put("additionalneeds", additionalNeeds);

        // Serialize back to JSON
        String newJsonString = objectMapper.writeValueAsString(bookingdataNode);

        System.out.println("New Json:"+newJsonString);

        return newJsonString;
    }

    public static Properties readPropertiesFile(String fileName) {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
