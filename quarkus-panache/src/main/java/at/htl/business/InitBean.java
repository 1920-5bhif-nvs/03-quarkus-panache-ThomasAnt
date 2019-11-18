package at.htl.business;

import at.htl.model.Graveyard;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class InitBean {

    @Transactional
    void init(@Observes StartupEvent ev){
        System.out.println("Init");
        Graveyard graveyard = new Graveyard();
        graveyard.location = "Linz";
        graveyard.name = "St. Barbara";
        graveyard.numberOfGraves = 20000L;

        Graveyard graveyard2 = new Graveyard();
        graveyard2.location = "Leonding";
        graveyard2.name = "hey";
        graveyard2.numberOfGraves = 10000L;

        graveyard2.persist();
        graveyard.persist();
    }
}
