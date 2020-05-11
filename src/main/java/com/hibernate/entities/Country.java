package com.hibernate.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @NaturalId
    @Column(name = "ISO_code")
    private String isoCode;

    @Column(name = "Name")
    private String name;

    public Country(String code, String name) {
        isoCode = code;
        this.name = name;
    }
}
