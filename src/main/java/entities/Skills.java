package entities;

import enums.SkillType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Skills {

    @Id
    @Column(name = "Id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "Type")
    private SkillType type;

    @Column(name = "Skill")
    private String skill;
}
