package entities;

import enums.Role;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Employee {

    @Id
    @Column
    private int id;

    @Column
    private String Userid;

    @Column(name = "First name")
    private String firstName;

    @Column(name = "Last name")
    private String lastName;

    @Column(name = "Skills")
    private int skills;

    @Column(name = "Address")
    private int address;

    @Enumerated(EnumType.STRING)
    @Column(name = "Role")
    private Role role;

}
