package pl.mlisowski.lab4.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Class extends BaseEntity {

    @Column
    private String groupName;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();

    @Column
    private int maxStudents;

    @Builder
    public Class(Long id, String uuid, String groupName, Set<Student> students, int maxStudents) {
        super(id, uuid);
        this.groupName = groupName;
        this.students = students;
        this.maxStudents = maxStudents;
    }

    public void addStudent(Student student) {
        this.students.add(student);
        student.getClasses().add(this);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
        student.getClasses().remove(this);
    }

}
