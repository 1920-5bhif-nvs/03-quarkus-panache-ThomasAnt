package at.htl.business;

import at.htl.GraveyardService;
import at.htl.model.*;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class InitBean {
    private static final int numberOfGraveyards = 20;

    //@Inject
    //EntityManager em;


    @Transactional
    void init(@Observes StartupEvent ev){
        System.out.println("Init");

        for(int i = 1;i <=numberOfGraveyards;i++){
            Graveyard graveyard = new Graveyard();
            graveyard.location = "Location " + i;
            graveyard.name = "Name " + i;
            graveyard.numberOfGraves = 1000L * i;
            graveyard.persist();
            //em.persist(graveyard);
            GraveyardKeeper graveyardKeeper = new GraveyardKeeper();
            graveyardKeeper.firstName = "Max " + i;
            graveyardKeeper.lastName = "Mustermann " + i;
            graveyardKeeper.birthDate = Date.valueOf(LocalDate.now().minus(3*i, ChronoUnit.YEARS));
            graveyardKeeper.persist();
            Contract contract = new Contract();
            contract.graveyard = graveyard;
            contract.graveyardKeeper = graveyardKeeper;
            contract.start = Date.valueOf(LocalDate.now().minus(5*i,ChronoUnit.MONTHS));
            contract.salary = 100L * i;
            contract.persist();
            for(int j = 1; j<= 10;j++){
                FamilyGrave fg = new FamilyGrave();
                fg.familyName = "Mustermann " + ((i-1) * 10 +j);
                fg.numberBurried = 2L*i*j;
                fg.price = 100L * i * j;
                fg.graveyard = graveyard;
                fg.persist();
            }
        }
    }
}
