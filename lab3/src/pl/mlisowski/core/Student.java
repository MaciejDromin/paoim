package pl.mlisowski.core;

import lombok.*;
import pl.mlisowski.enums.StudentCondition;

import java.util.Comparator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Student implements Comparable<Student> {

    private String name;
    private String surrname;
    private StudentCondition condition;
    private int birthYear;
    private double points;

    public void print() {
        System.out.println(this);
    }

    @Override
    public boolean equals(Object o) {
        Student s = (Student) o;
        return this.name.equals(s.getName()) && this.surrname.equals(s.getSurrname()) && this.birthYear == s.getBirthYear();
    }

    @Override
    public int compareTo(Student o) {
        return Double.compare(this.points, o.getPoints());
    }
}
