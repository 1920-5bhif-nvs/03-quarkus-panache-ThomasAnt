package at.htl.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class Graveyard extends PanacheEntity {
    public String name;
    public String location;
    public Long numberOfGraves;

    public Graveyard() {
    }

    public static List<Graveyard> findByLocation(String location){
        return list("location",location);
    }

    @Override
    public String toString() {
        return "Graveyard{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", numberOfGraves=" + numberOfGraves +
                '}';
    }
}
