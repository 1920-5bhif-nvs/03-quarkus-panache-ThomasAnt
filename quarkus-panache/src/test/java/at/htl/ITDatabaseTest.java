package at.htl;

import at.htl.model.Graveyard;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.api.Assertions;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;

import static io.restassured.RestAssured.given;
import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.fail;

@QuarkusTest
public class ITDatabaseTest {

    @Inject
    DataSource ds;

    @Test
    public void testGraveyardTableCreation(){
        String tablename = "graveyard";
        Table table = new Table(ds,tablename);
        try {
            output(table).toConsole();
        }
        catch (Exception ex){
            fail("Table: " + tablename + " does not exist");
        }
    }

    @Test
    public void testGraveyardKeeperTableCreation(){
        String tablename = "graveyardkeeper";
        Table table = new Table(ds,tablename);
        try {
            output(table).toConsole();
        }
        catch (Exception ex){
            fail("Table: " + tablename + " does not exist");
        }
    }

    @Test
    public void testGraveTableCreation(){
        String tablename = "grave";
        Table table = new Table(ds,tablename);
        try {
            output(table).toConsole();
        }
        catch (Exception ex){
            fail("Table: " + tablename + " does not exist");
        }
    }

    @Test
    public void testContractTableCreation(){
        String tablename = "contract";
        Table table = new Table(ds,tablename);
        try {
            output(table).toConsole();
        }
        catch (Exception ex){
            fail("Table: " + tablename + " does not exist");
        }
    }

    @Test
    public void checkCorrectDataGraveyard(){
        Table table = new Table(ds,"graveyard");
        assertThat(table).column("name")
                .value().isEqualTo("Name 1")
                .value().isEqualTo("Name 2");
        assertThat(table).column("location")
                .value().isEqualTo("Location 1")
                .value().isEqualTo("Location 2");
        assertThat(table).column("numberofgraves")
                .value().isEqualTo(1000)
                .value().isEqualTo(2000);
    }

    @Test
    public void checkCorrectDataGraveyardKeeper(){
        Table table = new Table(ds,"graveyardKeeper");
        assertThat(table).column("firstname")
                .value().isEqualTo("Max 1")
                .value().isEqualTo("Max 2");
        assertThat(table).column("lastname")
                .value().isEqualTo("Mustermann 1")
                .value().isEqualTo("Mustermann 2");
    }

    @Test
    public void checkCorrectGrave(){
        Table table = new Table(ds,"grave");
        assertThat(table).column("familyname")
                .value().isEqualTo("Mustermann 1")
                .value().isEqualTo("Mustermann 2");
        assertThat(table).column("numberburried")
                .value().isEqualTo(2)
                .value().isEqualTo(4);
        assertThat(table).column("price")
                .value().isEqualTo(100)
                .value().isEqualTo(200);
    }

}
