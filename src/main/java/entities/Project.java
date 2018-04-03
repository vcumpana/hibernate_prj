package entities;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Project {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    @NaturalId
    private String ProjectCode;

    @Column
    private String Name;

    @Column
    private String Description;


    @ManyToMany
    private Set<Employee> employees;

}
