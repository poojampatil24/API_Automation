package PracticeTestCases;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class JiraSoftware {
    private static final String JIRA_USERNAME = "pooja.patil@afourtech.com";
    private static final String APIToken = "ATATT3xFfGF08bkwIzlbt9DFY-jeeWxPm7QJzAxm0R0ILFSNjspC_kYVQB5XJEM1tnu6q6fJMq_qzvGtLQ-jppDxp1mczvk2uoShcOxd_deOeEyXGTuoNWKX4TWbFvjJs1iJWf5p2t48eSXU2PITgxomE1aIN2NG-2TfGUAoPs5rrHbUsCnUW84=E5370C3D";
    private static final String JIRA_URL = "https://pooja-patil.atlassian.net/rest/api/2/project";
//    private static final String JIRA_URL = "https://pooja-patil.atlassian.net/rest/api/2/project/ATU/version";


//    private static final String JIRA_URL = "https://pooja-patil.atlassian.net/rest/api/2/project/ATU/isseu";
//private static final String JIRA_URL = "https://pooja-patil.atlassian.net/rest/zapi/latest";


    String projectKey = "ATU";
    String versionName = "10000";

    @Test
    public void test() {
        // Set the authentication scheme
         PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName(JIRA_USERNAME);
        authScheme.setPassword(APIToken);
        RestAssured.authentication = authScheme;
        // Send the request to search for the project ID
         String response = RestAssured.given()
                .contentType(ContentType.JSON)
                .get(JIRA_URL).asString();

        System.out.println(response);


        Response response1 = RestAssured.given()
                .contentType(ContentType.JSON)
                .get(JIRA_URL);


        // Parse the response to get the project ID
//        JsonPath jsonPath = response1.jsonPath();
//        String projectId = jsonPath.get("[0].id");
//        System.out.println("Project ID: " + projectId);


        // Parse the response to get the Version ID
//        JsonPath jsonPath = response1.jsonPath();
//        String version = jsonPath.get("values[1].id");
//        System.out.println("Version ID: " + version);











//        Response response = RestAssured.given()
//                .header("Authorization", "Bearer "+APIToken+"")
//                .when()
//                .get(JIRA_URL)
//                .then()
//                .statusCode(200)
//                .extract()
//                .response();

        // Parse the response to get the project ID
//        JsonPath jsonPath=new JsonPath(response);
//        JsonPath jsonPath = response.jsonPath();
//        String projectId = jsonPath.get("[0].id");

//        System.out.println("Project ID: " + projectId);
    }
}
