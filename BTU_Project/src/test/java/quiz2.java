import io.qameta.allure.Epic;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;

import static io.restassured.RestAssured.given;

@Epic("Quiz Tests")
public class Quiz2 {
    String userURI="https://bookstore.toolsqa.com/Account/v1/User";

    @Test(description = "wrong type of password")
    public void test1(){
        String message =  given().contentType(ContentType.JSON).body("{\n" +
                        "  \"userName\": \"ADMIN\",\n" +
                        "  \"password\": \"123\"\n" +
                        "}")
                .when().post(userURI).then().
                log().all()
                .extract()
                .jsonPath().getString("message");
        Assert.assertEquals(message,"Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer.");
    }


    @Test(description = "User exists")
    public void test2(){
        String message =  given().contentType(ContentType.JSON).body("{\n" +
                        "  \"userName\": \"adminadmin\",\n" +
                        "  \"password\": \"@asdASD123\"\n" +
                        "}")
                .when().post(userURI).then().
                log().all()
                .extract()
                .jsonPath().getString("message");
        Assert.assertEquals(message,"User exists!");
    }


    @Test(description = "User exists")
    public void test3(){
        String message =  given().contentType(ContentType.JSON).body("{\n" +
                        "  \"userName\": \"\",\n" +
                        "  \"password\": \"@asdASD123\"\n" +
                        "}")
                .when().post(userURI).then().
                log().all()
                .extract()
                .jsonPath().getString("message");
        Assert.assertEquals(message,"UserName and Password required.");
    }


}