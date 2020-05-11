package com.hibernate.entities;

import com.hibernate.enums.Role;
import com.hibernate.enums.Status;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;

@Entity
@Data
@NoArgsConstructor
@SQLDelete(sql = "UPDATE Employee SET status = 'INACTIVE' where id = ?")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

  @Column(name = "userId")
  @NaturalId
    private String userId;

    private String firstName;

    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employee_skills" , joinColumns={@JoinColumn(name="employee_id")},
            inverseJoinColumns={@JoinColumn(name="skill_id")})
    private Set<Skills> skills;

    @OneToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Address address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Transient
    private Integer age;

    @Temporal(value = TemporalType.DATE)
    private Date dateOfBirth;

    @ManyToMany
    @JoinTable(name = "employee_projects",  joinColumns={@JoinColumn(name="employee_id")},
            inverseJoinColumns={@JoinColumn(name="project_id")})
    private Set<Project> projects;

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
