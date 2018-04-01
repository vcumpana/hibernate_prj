package entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Country {

    @Id
    @Column(name = "Id")
    private int id;

    @Column(name = "ISO_code")
    private String isoCode;

    @Column(name = "Name")
    private String name;
}
