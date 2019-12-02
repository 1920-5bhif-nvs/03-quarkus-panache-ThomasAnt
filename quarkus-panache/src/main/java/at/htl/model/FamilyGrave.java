package at.htl.model;

import javax.persistence.Entity;

@Entity
public class FamilyGrave extends Grave {
    public String familyName;
    public Long numberBurried;
}
