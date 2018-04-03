package entities;

import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@Data
@Entity
//@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;

    public Address(Country country, String city, String street, String strNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.strNumber = strNumber;
    }


    @ManyToOne
    private Country country;

    @Column(name = "City")
    private String city;

    @Column(name = "Street")
    private String street;

    @Column(name = "Street number")
    private String strNumber;
}
