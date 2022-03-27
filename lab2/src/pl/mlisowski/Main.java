package pl.mlisowski;

import pl.mlisowski.core.Class;
import pl.mlisowski.core.ClassContainer;
import pl.mlisowski.core.Student;
import pl.mlisowski.enums.StudentCondition;

public class Main {

    public static void main(String[] args) {
        ClassContainer c = new ClassContainer();
        c.addClass("Testowe", 10);
        Class cla = c.getClass("Testowe");
        Student s = new Student("Test", "Testowy", StudentCondition.NOT_PRESENT, 2000, 20.0);
        cla.addStudent(s);
        Student tmp = cla.searchPartial("st");
        tmp.print();
        cla.changeCondition(tmp, StudentCondition.SICK);
        tmp = cla.search("Test");
        tmp.print();
        System.out.println(cla.countByCondition(StudentCondition.SICK));
        System.out.println(cla.countByCondition(StudentCondition.NOT_PRESENT));
        cla.addPoints(tmp, 20);
        tmp.print();
        System.out.println(cla.getStudent(tmp));
        cla.summary();
        Student s2 = new Student("Test2", "Testowy", StudentCondition.NOT_PRESENT, 2000, 10.0);
        cla.addStudent(s2);
        c.summary();
    }
}
