package entities;

import enums.Role;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   // @Column
    private int id;

   // @Column
    @NaturalId
    private String Userid;


    private String firstName;

    //@Column(name = "Last name")
    private String lastName;


    @ManyToMany()
    private Set<Skills> skills;


    @OneToOne
    private Address address;

    @Enumerated(EnumType.STRING)
  //  @Column(name = "Role")
    private Role role;

}
