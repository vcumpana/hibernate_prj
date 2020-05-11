package com.hibernate.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@NamedQuery(name = "Company.count", query = "select count(*) from Company c join c.employees e where c.Name = :name")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String Name;

    @OneToMany
    private Set<Project> projects;

    @ManyToMany
    @JoinTable(name = "company_employees" )
    private Set<Employee> employees;

    public Company(String name, Set<Project> projects, Set<Employee> employees) {
        Name = name;
        this.projects = projects;
        this.employees = employees;
    }
}
