package entities;

import lombok.Data;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
//@Table(name = "Address")
public class Address {

    @Id
    @Column(name = "Id")
    private int id;

    @Column(name = "Country")
    private int countryId;

    @Column(name = "City")
    private String city;

    @Column(name = "Street")
    private String street;

    @Column(name = "Street number")
    private String strNumber;
}
