package pl.mlisowski.lab4.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import pl.mlisowski.lab4.domain.enums.StudentCondition;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Slf4j
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

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    Set<Class> classes = new HashSet<>();

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

    public void addClass(Class klasa) {
        this.classes.add(klasa);
        klasa.getStudents().add(this);
    }

    public void remove(Class klasa) {
        this.classes.remove(klasa);
        klasa.getStudents().remove(this);
    }

}
