package com.hibernate.entities;

import com.hibernate.enums.SkillType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

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
