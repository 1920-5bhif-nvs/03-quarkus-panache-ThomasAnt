package at.htl.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Grave extends PanacheEntity {

    public Long price;

    @ManyToOne
    public Graveyard graveyard;

}
