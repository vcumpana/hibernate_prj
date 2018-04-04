package entities;

import enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   // @Column
    private int id;

   // @Column
    @NaturalId
    private String userId;


    private String firstName;

    //@Column(name = "Last name")
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Skills> skills;

    @OneToOne
    private Address address;

    @Enumerated(EnumType.STRING)
  //  @Column(name = "Role")
    private Role role;

    public Employee(String userid, String firstName, String lastName, Set<Skills> skills, Address address, Role role) {
        this.userId = userid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.address = address;
        this.role = role;
    }
}
