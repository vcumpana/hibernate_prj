package entities;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Project {



    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    @NaturalId
    private String projectCode;

    @Column
    private String name;

    @Column
    private String description;


    @ManyToMany
    private Set<Employee> employees;


    public Project(String projectCode, String name, String description, Set<Employee> employees) {
        this.projectCode = projectCode;
        this.name = name;
        this.description = description;
        this.employees = employees;
    }
}
