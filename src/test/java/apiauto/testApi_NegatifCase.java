package apiauto;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;

public class testApi_NegatifCase {
    //Using Wrong MEthod
    @Test
    public void Get(){
        given().when()
                .post("https://reqres.in/api/users/2")
                .then().log().all()
                .assertThat().statusCode(415)
//                .assertThat().body("per-page", equalTo(null))
//                .assertThat().body("page", equalTo(2));
                    ;
    }
//Using Wrong URL
    @Test
    public void Post(){
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
                .post("https://reqres.in/a")
                .then().log().all()
                .assertThat()
                .statusCode(404);
//                .body("name", equalTo(valueofName))
//                .body("job", equalTo(valueofJob));

    }

//Wrong Method
    @Test
    public void Negatif_testValidateJsonSchemaGetSingleUser(){
        RestAssured.baseURI = "https://reqres.in/";
        int userToGet=5;
        File file= new File("src/test/resources/jasonSchema/Negative_getSingleUseeSchema.jason");
        given().log().all()
                .when().post("api/users" + userToGet)
                .then().log().all()
                .assertThat().statusCode(415);
//                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(file));
    }
}

