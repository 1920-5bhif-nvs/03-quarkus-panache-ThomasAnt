package at.htl.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Contract extends PanacheEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    public Graveyard graveyard;

    @ManyToOne(cascade = CascadeType.ALL)
    public GraveyardKeeper graveyardKeeper;

    public Date start;
    public Long salary;
}
