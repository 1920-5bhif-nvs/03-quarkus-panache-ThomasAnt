package at.htl.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Graveyard extends PanacheEntity {
    public String name;
    public String location;
    public Long numberOfGraves;

    public Graveyard() {
    }
}
