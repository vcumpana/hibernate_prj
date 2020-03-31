package entities;

import enums.SkillType;
import java.util.Set;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type")
    private SkillType type;

    @Column(name = "Skill")
    private String skill;
//
//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @ManyToMany
//    private Set<Employee> employees;

    public Skills(SkillType skillType, String skill){
        this.type = skillType;
        this.skill = skill;
    }
}
