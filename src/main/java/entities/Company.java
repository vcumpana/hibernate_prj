package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String Name;

    @OneToMany
    private Set<Project> projects;

    @ManyToMany
    private Set<Employee> employees;
}
