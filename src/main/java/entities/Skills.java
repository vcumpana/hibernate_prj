package entities;

import enums.SkillType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public Skills(SkillType skillType, String skill){
        this.type = skillType;
        this.skill = skill;
    }
}
