package entities;

import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;

    @NaturalId
    @Column(name = "ISO_code")
    private String isoCode;

    @Column(name = "Name")
    private String name;
}
