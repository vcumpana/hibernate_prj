package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
//@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    private Country country;

    @Column(name = "City")
    private String city;

    @Column(name = "Street")
    private String street;

    @Column(name = "Street_number")
    private String strNumber;

    public Address(){}

    public Address(Country country, String city, String street, String strNumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.strNumber = strNumber;
    }
}
