package at.htl.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Graveyard extends PanacheEntity {

    /*
    * **** HIbernate ****
    * fields private
    * Getter + Setter
    * Constructor
    * dont extend Panach Entity
    *
    * @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    * private Long id;
    *
    * @NamedQueries({
    *   @NamedQuery(name = "Graveyard.findall",query = "Select g from Graveyard g"),
    *   @NamedQuery(name = "Graveyard.findByLocation",query = "SELECT g from Graveyard g where g.location=:location")
    * })
    * */


    public String name;
    public String location;
    public Long numberOfGraves;

    public Graveyard() {
    }

    public static List<Graveyard> findByLocation(String location){
        return list("location",location);
    }

    @Override
    public void delete() {
        List<Contract> contracts= Contract.list("graveyard",this);
        System.out.println(contracts.size());
        for (Contract contract:contracts) {
            contract.delete();
        }
        List<Grave> graves = Grave.list("graveyard",this);
        System.out.println(graves.size());
        for (Grave grave:graves) {
            grave.delete();
        }

        super.delete();
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
