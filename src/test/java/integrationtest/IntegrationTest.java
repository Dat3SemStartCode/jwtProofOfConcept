package integrationtest;

import org.junit.BeforeClass;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import java.net.MalformedURLException;
import javax.servlet.ServletException;
import static org.hamcrest.Matchers.*;
import org.junit.AfterClass;
import org.junit.Test;

public class IntegrationTest {

  private static final int SERVER_PORT = 8888;
  private static final String APP_CONTEXT = "/jwtdemo";

  public IntegrationTest() {
  }
  private static String securityToken;

  
  @Test
  public void serverIsRunning() {
    given().when().get().then().statusCode(200);
  }
  
  //Utility method to login and set the securityToken
  private static void login(String role, String password) {
    String json = String.format("{username: \"%s\", password: \"%s\"}", role, password);
    System.out.println(json);
    securityToken = given()
            .contentType("application/json")
            .body(json)
            .when().post("/api/login")
            .then()
            .extract().path("token");
    System.out.println("Token: --> " + securityToken);

  }

  private void logOut() {
    securityToken = null;
  }

  @BeforeClass
  public static void setUpBeforeAll() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = SERVER_PORT;
    RestAssured.basePath = APP_CONTEXT;
    RestAssured.defaultParser = Parser.JSON;
  }

  @Test
  public void testRestNoAuthenticationRequired() {
    given()
            .contentType("application/json")
            .when()
            .get("/api/info").then()
            .statusCode(200)
            .body("msg", equalTo("Hello anonymous"));
  }

 
  @Test
  public void testRestForAdmin() {
    login("admin", "test");
    given()
            .contentType("application/json")
            .accept(ContentType.JSON)
            .header("x-access-token", securityToken)
            .when()
            .get("/api/info/admin").then()
            .statusCode(200)
            .body("msg", equalTo("Hello from ADMIN: "+"admin"));
            //.body("serverTime", notNullValue());
  }
 
  @Test
  public void testRestForUser() {
    login("user", "test");
    given()
            .contentType("application/json")
            .header("x-access-token",  securityToken)
            .when()
            .get("/api/info/user").then()
            .statusCode(200)
            .body("msg", equalTo("Hello from USER: "+"user"));
  }

  @Test
  public void userNotAuthenticated() {
    logOut();
    given()
            .contentType("application/json")
            .when()
            .get("/api/info/user").then()
            .statusCode(403);
  //          .body("error.message", equalTo("No authorization header provided"));
  }

  @Test
  public void adminNotAuthenticated() {
    logOut();
    given()
            .contentType("application/json")
            .when()
            .get("/api/info/user").then()
            .statusCode(403);
            //.body("error.message", equalTo("No authorization header provided"));

  }

}
