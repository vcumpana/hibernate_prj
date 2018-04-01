package entities;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Project {

    @Id
    @Column
    private int id;

    @Column
    private String ProjectCode;

    @Column
    private String Name;

    @Column
    private String Description;

    @Column
    private int Employees;

}
