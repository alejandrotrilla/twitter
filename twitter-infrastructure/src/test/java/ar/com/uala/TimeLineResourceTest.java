package ar.com.uala;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class TimeLineResourceTest {
    @Test
    void testGetEndpoint() {
        given()
          .when().get("/time-line/1")
          .then()
             .statusCode(200);
    }
}