package pl.mlisowski.lab4.domain;

import lombok.*;
import pl.mlisowski.lab4.domain.enums.StudentCondition;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student extends BaseEntity {

    @Column
    private String name;

    @Column
    private String surrname;

    @Column
    @Enumerated(EnumType.STRING)
    private StudentCondition condition;

    @Column
    private int birthYear;

    @Column
    private double points;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Class> classes;

    @Builder
    public Student(Long id, String uuid, String name, String surrname, StudentCondition condition, int birthYear, double points, Set<Class> classes) {
        super(id, uuid);
        this.name = name;
        this.surrname = surrname;
        this.condition = condition;
        this.birthYear = birthYear;
        this.points = points;
        this.classes = classes;
    }
}
