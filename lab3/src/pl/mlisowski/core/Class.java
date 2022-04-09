package pl.mlisowski.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mlisowski.enums.StudentCondition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Class {

    private String groupName;
    private List<Student> students;
    private int maxStudents;

    public boolean addStudent(Student s) {
        if(students.contains(s)) return false;
        students.add(s);
        return true;
    }

    public void addPoints(Student s, double points) {
        students.get(students.indexOf(s)).setPoints(s.getPoints()+points);
    }

    public Student getStudent(Student s ){
        if(students.get(students.indexOf(s)).getPoints()>0) return null;
        return students.remove(students.indexOf(s));
    }

    public void changeCondition(Student s, StudentCondition condition) {
        students.get(students.indexOf(s)).setCondition(condition);
    }

    public void removePoints(Student s, double points) {
        students.get(students.indexOf(s)).setPoints(s.getPoints()-points);
    }

    public Student search(String surrname) {
        return students.stream().filter(s -> s.getName().equals(surrname)).findAny().orElse(null);
    }

    public Student searchPartial(String reg) {
        return students.stream().filter(s -> s.getName().contains(reg)).findAny().orElse(null);
    }

    public int countByCondition(StudentCondition cond) {
        List<Student> ret = students.stream()
                .filter(s -> s.getCondition() == cond)
                .collect(Collectors.toList());
        return ret.size();
    }

    public void summary() {
        for(Student s : students) s.print();
    }

    public List<Student> sortByName() {
        List<Student> ret = new ArrayList<>(students);
        ret.sort(Comparator.comparing(Student::getName));
        return ret;
    }

    public List<Student> sortByPoints() {
        List<Student> ret = new ArrayList<>(students);
        ret.sort(Comparator.comparing(Student::getPoints));
        return ret;
    }

    public Student max(){
        return Collections.max(students);
    }

}
