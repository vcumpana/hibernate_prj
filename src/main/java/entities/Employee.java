package entities;

import enums.Role;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    @NaturalId
    private String Userid;

    @Column(name = "First name")
    private String firstName;

    @Column(name = "Last name")
    private String lastName;

    @Column(name = "Skills")
    private int skills;

    @Column(name = "Address")
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private Role role;

}
