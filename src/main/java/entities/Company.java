package entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String Name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Set<Project> projects;

    @ManyToMany
    private Set<Employee> employees;

    public Company(String name, Set<Project> projects, Set<Employee> employees) {
        Name = name;
        this.projects = projects;
        this.employees = employees;
    }
}
