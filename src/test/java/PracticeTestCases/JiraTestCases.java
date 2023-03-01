package PracticeTestCases;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.testng.annotations.Test;
//import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
//import com.thed.zephyr.cloud.rest.client.JwtGenerator;

import java.net.URI;
import java.net.URISyntaxException;

public class JiraTestCases {

    public static final String zephyrBaseUrl = "https://prod-vortexapi.zephyr4jiracloud.com/api/v1/jwt/generate";

    @Test
    public void test() throws URISyntaxException {
        // Set the authentication scheme

        String accessKey = "YmYxM2NmMDAtZTcyYy0zMmU1LWI4NTYtMmY2ZWI4YTFmM2JkIDYyYzdjZDNiYTdkZTFlN2M3YzJlYWRhZCBVU0VSX0RFRkFVTFRfTkFNRQ";

        String secretKey = "XdAz2yA-pP5mjIq54_TruVKRAWfBoHxivaQkdd_LIVU";

        String accountId = "62c7cd3ba7de1e7c7c2eadad";
//        ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, accountId).build();
//        JwtGenerator jwtGenerator = client.getJwtGenerator();

        // API to which the JWT token has to be generated
        String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/cycle?expand=&clonedCycleId=";

        URI uri = new URI(createCycleUri);
        int expirationInSec = 360;
//        String jwt = jwtGenerator.generateJWT("GET", uri, expirationInSec);

        // Print the URL and JWT token to be used for making the REST call
        System.out.println("FINAL API : " +uri.toString());
//        System.out.println("JWT Token : " +jwt);
    }
}
