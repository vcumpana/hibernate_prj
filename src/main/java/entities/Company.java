package entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String Name;

    @Column
    private int Projects;

    @Column
    private int Employees;
}
