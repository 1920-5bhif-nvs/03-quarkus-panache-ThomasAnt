package at.htl;

import at.htl.model.Graveyard;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ITContractEndpoint {

    @Test
    public void testGraveyardPostEndpoint (){

        given().when().get("/contract")
                .then().statusCode(200).body("$.size()",is(10),
                "[0].graveyard.id",is(1),
                "[0].graveyardKeeper.id",is(2),
                "[0].salary",is(100));
    }

    @Test
    public void testGravesForGraveyardkeeper(){
        given().when().get("/contract/2").then().statusCode(200)
                .body("$.size()",is(10),
                        "[0].graveyard.id",is(1),
                        "[0].price",is(100),
                        "[0].familyName",is("Mustermann 1"),
                        "[0].numberBurried",is(2));
    }

    @Test
    public void getSalaryToPay(){
        given().when().get("/contract/salary").then().statusCode(200)
                .body(is("21000"));
    }
}
