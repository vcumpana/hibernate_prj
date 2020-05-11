package com.hibernate.entities;

import com.hibernate.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@SQLDelete(sql = "UPDATE Project SET status = 'INACTIVE' where id = ?")
public class Project {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @NaturalId
    private String projectCode;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees;

    @OneToOne
    private Employee manager;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Project(String projectCode, String name, String description, Set<Employee> employees, Status status) {
        this.projectCode = projectCode;
        this.name = name;
        this.description = description;
      //  this.employees = employees;
        this.status = status;
    }
}
