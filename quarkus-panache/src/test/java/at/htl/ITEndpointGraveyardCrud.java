package at.htl;

import at.htl.model.Graveyard;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonValue;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ITEndpointGraveyardCrud {

    @Inject
    @RestClient
    GraveyardService graveyardService;

    @Test
    public void testGraveyardGetEndpoint (){
        given().when().get("/graveyard/1")
                .then().statusCode(200).body(is("{\"id\":1,\"location\":\"Location 1\",\"name\":\"Name 1\",\"numberOfGraves\":1000}"));
    }
    @Test
    public void testGraveyardPostEndpoint (){
        Graveyard graveyard = new Graveyard();
        graveyard.name = "Test Graveyard";
        graveyard.location = " Test Location";
        graveyard.numberOfGraves = 3000L;
        given().when().body(graveyard).post("/graveyard")
                .then().statusCode(200);
    }

    @Test
    public void testGraveyardGetAllEndpoint(){
        given()
                .when().get("/graveyard?limit=10&offset=1")
                .then().statusCode(200)
                .body("$.size()",is(10),
                        "[0].name",is("Name 11"),
                        "[0].location",is("Location 11"),
                        "[0].numberOfGraves",is(11000));
        //System.out.println(graveyardService.getAll(1,10));
        //System.out.println(graveyardService.getById(1L).asJsonObject().getString("location"));
        JsonArray array = graveyardService.getAll(1,10);
        for (JsonValue asdf:array) {
            System.out.println(asdf.asJsonObject().getString("location"));
        }
        JsonValue value = graveyardService.getById(1L);
        System.out.println(value.asJsonObject().getString("location"));
        //
    }

    @Test
    public void testGraveyardDelete(){
        given().when().delete("/graveyard/1")
                .then().statusCode(200);

    }
}
