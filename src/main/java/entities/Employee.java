package entities;

import enums.Role;
import enums.Status;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "my_employee")
@Data
@NoArgsConstructor
@SQLDelete(sql = "UPDATE Employee SET status = 'INACTIVE' where id = ?")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String userId;

    private String firstName;

    private String lastName;

    private LocalDate date;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "employee_skills" , joinColumns={@JoinColumn(name="employee_id")},
        inverseJoinColumns={@JoinColumn(name="skill_id")})
    private Set<Skills> skills;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @Enumerated(EnumType.ORDINAL)
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
