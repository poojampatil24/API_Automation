package org.APIClient.Utilities;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class WrapperFunctions {

    public static SimpleDateFormat formatter;
    public static Timestamp timestamp;


    //Get timestamp
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

    //Read Json file
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
