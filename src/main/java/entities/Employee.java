package entities;

import enums.Role;
import enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@SQLDelete(sql = "UPDATE Employee SET status = 'INACTIVE' where id = ?")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NaturalId
    private String userId;

    private String firstName;

    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Skills> skills;

    @OneToOne
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Employee(String userid, String firstName, String lastName, Set<Skills> skills, Address address, Role role, Status status) {
        this.userId = userid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
        this.address = address;
        this.role = role;
        this.status = status;
    }
}
