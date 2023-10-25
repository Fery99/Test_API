package apiauto;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;

public class testApi_PostifCase {
    @Test
    public void useGet(){
        given().when()
                .get("https://reqres.in/api/users?page=2")
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("per_page", Matchers.equalTo(6))
                .assertThat().body("page", Matchers.equalTo(2));

    }

    @Test
    public void usePost(){
        String valueofName, valueofJob;
        valueofName = "martin";
        valueofJob = "Kuli";
        JSONObject BodyObj= new JSONObject();
        BodyObj.put("name", valueofName);
        BodyObj.put("job", valueofJob);

        given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(BodyObj.toString())
                .when()
                .post("https://reqres.in/api/users")
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .body("name", Matchers.equalTo(valueofName))
                .body("job", Matchers.equalTo(valueofJob));

    }


    @Test
    public void Positif_testValidateJsonSchemaGetSingleUser(){
        RestAssured.baseURI = "https://reqres.in/";
        int userToGet=5;
        File file= new File("src/test/resources/jasonSchema/Positive_getSingleUseeSchema.jason");
        given().log().all()
                .when().get("api/users" + userToGet)
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(file));


    }
}
