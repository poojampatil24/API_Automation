package PracticeTestCases;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.testng.annotations.Test;

import java.util.Date;

public class NewJWTToken {
    @Test
    public void test(){
        String baseUrl = "https://your-zephyr-instance.com";
        String accessKey = "YmYxM2NmMDAtZTcyYy0zMmU1LWI4NTYtMmY2ZWI4YTFmM2JkIDYyYzdjZDNiYTdkZTFlN2M3YzJlYWRhZCBVU0VSX0RFRkFVTFRfTkFNRQ";
        String secretKey = "XdAz2yA-pP5mjIq54_TruVKRAWfBoHxivaQkdd_LIVU";

        long now = System.currentTimeMillis();
        Date issuedAt = new Date(now);
        Date expiresAt = new Date(now + 5 * 60 * 1000); // 5 minutes from now

        String jwtToken = Jwts.builder()
                .setIssuer(accessKey)
                .setIssuedAt(issuedAt)
                .setExpiration(expiresAt)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        System.out.println("JWT token: " + jwtToken);
    }
}
