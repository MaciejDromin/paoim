package pl.mlisowski.lab4.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
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

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Student> students;

    @Column
    private int maxStudents;

    @Builder
    public Class(Long id, String uuid, String groupName, Set<Student> students, int maxStudents) {
        super(id, uuid);
        this.groupName = groupName;
        this.students = students;
        this.maxStudents = maxStudents;
    }

}
