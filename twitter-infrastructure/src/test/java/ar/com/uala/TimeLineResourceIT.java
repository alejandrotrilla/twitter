package ar.com.uala;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

@QuarkusIntegrationTest
class TimeLineResourceIT {
    @Test
    void twoTweetsForTwoUsers() {
        given().when()
               .contentType(ContentType.JSON)
               .put("/user/2/start-following/1")
               .then()
               .statusCode(204);
        given().when()
               .contentType(ContentType.JSON)
               .put("/user/3/start-following/1")
               .then()
               .statusCode(204);
        given().when()
               .contentType(ContentType.JSON)
               .put("/user/3/start-following/2")
               .then()
               .statusCode(204);
        given().when()
               .contentType(ContentType.JSON)
               .body("{\"text\": \"First tweet from user 1\"}")
               .post("/tweet/1")
               .then()
               .statusCode(200);
        given().when()
               .contentType(ContentType.JSON)
               .body("{\"text\": \"First tweet from user 2\"}")
               .post("/tweet/2")
               .then()
               .statusCode(200);
        given().when()
               .contentType(ContentType.JSON)
               .get("/time-line/2")
               .then()
               .statusCode(200)
               .body(allOf(
                       not(containsString("First tweet from user 2")),
                       containsString("First tweet from user 1")
               ));
        given().when()
               .contentType(ContentType.JSON)
               .get("/time-line/3")
               .then()
               .statusCode(200)
               .body(allOf(
                       containsString("First tweet from user 1"),
                       containsString("First tweet from user 2")
               ));
    }
}
