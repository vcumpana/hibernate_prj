package entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Company {

    @Id
    @Column
    private int id;

    @Column
    private String Name;

    @Column
    private int Projects;

    @Column
    private int Employees;
}
