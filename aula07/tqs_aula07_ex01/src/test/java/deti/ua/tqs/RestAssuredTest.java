package deti.ua.tqs;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class RestAssuredTest {

    @Test
    public void whenGetUrlThenStatusCode200() {
        String url = "https://jsonplaceholder.typicode.com/todos";
        given().when().get(url).then().assertThat().statusCode(200);
    }

    @Test
    public void whenGetTodoThenCheckTitle() {
        String url4 = "https://jsonplaceholder.typicode.com/todos/4";
        String title = "et porro tempora";
        given()
        .when()
                .get(url4)
        .then().assertThat()
                .statusCode(200)
                .and().body("title", equalTo(title))
                .and().body("id", equalTo(4));
    }

    @Test
    public void whenGetTodoLIstThenCheckElements() {
        int id198 = 198;
        int id199 = 199;
        String url = "https://jsonplaceholder.typicode.com/todos";
        given()
        .when()
                .get(url)
        .then().assertThat()
                .statusCode(200)
                .and().body("id", hasItems(id198, id199));
    }
}
