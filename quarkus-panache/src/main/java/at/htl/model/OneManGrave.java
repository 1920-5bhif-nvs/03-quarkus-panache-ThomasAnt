package at.htl.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class OneManGrave extends Grave {
    public String firstName;
    public String lastName;
    public Date dateBurried;
}
