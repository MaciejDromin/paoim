package pl.mlisowski;

import pl.mlisowski.core.*;
import pl.mlisowski.core.Class;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    private ClassContainer cc;
    private JFrame frame;
    private JPanel panel;
    private List<JPanel> splist;
    private GenericTableModel<Class> classModel;
    private GenericTable<GenericTableModel<Class>, List<Class>, Class> classTable;
    private GenericTableModel<Student> studentModel;
    private GenericTable<GenericTableModel<Student>, List<Student>, Student> studentTable;

/*    public static void main(String[] args) {
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
    } */

    public static void main(String[] args) {
        Main m = new Main();
        m.startUp();
    }

    private void startUp() {
        this.cc = new ClassContainer();
        this.splist = new ArrayList<>();
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.frame = new JFrame("Lab3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.panel = new JPanel();
        panel.setLayout(new FlowLayout());

        Student s = Student.builder()
                .name("Test")
                .surrname("TestS")
                .birthYear(2020)
                .build();
        cc.addClass("test", 20);
        cc.addClass("test2", 15);
        ArrayList<Class> ct = new ArrayList<>();
        ct.add(cc.getClass("test"));
        ct.add(cc.getClass("test2"));
        ct.get(0).addStudent(s);
        s = Student.builder()
                .name("Test3")
                .surrname("TestS")
                .birthYear(2006)
                .build();
        ct.get(1).addStudent(s);
        classModel = new GenericTableModel<>(ct, new String[]{"Nazwa grupy", "Maksymalna ilosc"});
        classTable = new GenericTable<>(classModel, ct);

        JScrollPane sp = new JScrollPane(classTable.getTable());
        handleRowSelection(classTable.getTable());
        JButton bt = new JButton("Usun klase");
        JButton bt2 = new JButton("Edytuj klase");
        JButton bt3 = new JButton("Dodaj klase");
        JButton bt4 = new JButton("Posortuj");
        JPanel tp = new JPanel();
        JPanel tp2 = new JPanel();
        tp.setLayout(new FlowLayout());
        tp2.setLayout(new GridLayout(4, 1));
        tp.add(sp);
        tp2.add(bt);
        tp2.add(bt2);
        tp2.add(bt3);
        tp2.add(bt4);
        tp.add(tp2);
        bt.addActionListener(removeClassButtonEvent());
        bt2.addActionListener(editClassButtonEvent());
        bt3.addActionListener(addClassButtonEvent());
        bt4.addActionListener(sortClassButtonEvent());
        panel.add(tp);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private ActionListener sortClassButtonEvent() {
        return e -> {
            classTable.getList().sort(Comparator.comparing(Class::getMaxStudents));
            classTable.updateTable();
        };
    }

    private ActionListener addClassButtonEvent() {
        return e -> {

        };
    }

    private ActionListener editClassButtonEvent() {
        return e -> {

        };
    }

    private ActionListener removeClassButtonEvent() {
        return e -> {

        };
    }

    private void handleRowSelection(JTable table) {
        table.getSelectionModel().addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting()) showStudents(table.getValueAt(table.getSelectedRow(), 0));
        });
    }

    private void showStudents(Object valueAt) {
        if(valueAt instanceof String) {
            if(splist.size()>0) panel.remove(splist.remove(0));
            String cn = (String) valueAt;
            Class cla =  cc.getClass(cn);
            List<Student> sl = cla.getStudents();
            studentModel = new GenericTableModel<>(sl, new String[]{"Imie", "Nazwisko", "Data urodzenia"});
            studentTable = new GenericTable<>(studentModel, sl);

            JScrollPane sp = new JScrollPane(studentTable.getTable());
            JButton bt = new JButton("Usun studenta");
            JButton bt2 = new JButton("Edytuj studenta");
            JButton bt3 = new JButton("Dodaj studenta");
            JButton bt4 = new JButton("Posortuj");
            JTextField soringField = new JTextField("Wyszukaj Studenta");
            JPanel tp = new JPanel();
            JPanel tp2 = new JPanel();
            tp.setLayout(new FlowLayout());
            tp2.setLayout(new GridLayout(5, 1));
            tp.add(sp);
            tp2.add(bt);
            tp2.add(bt2);
            tp2.add(bt3);
            tp2.add(bt4);
            tp2.add(soringField);
            tp.add(tp2);
            bt.addActionListener(removeStudentButtonEvent());
            bt2.addActionListener(editStudentButtonEvent());
            bt3.addActionListener(addStudentButtonEvent());
            bt4.addActionListener(sortStudentButtonEvent());
            soringField.addActionListener(filterStudent(soringField, cn));
            splist.add(tp);
            panel.add(tp);
            frame.pack();
        }
    }

    private ActionListener filterStudent(JTextField field, String currentClass) {
        return e -> {
            String searchFor = field.getText();
            Class cla = cc.getClass(currentClass);
            if(searchFor.isEmpty()) {
                List<Student> sl = cla.getStudents();
                studentModel.setItems(sl);
                studentTable.setCustomList(sl);
                studentTable.updateTable();
            }else {
                Student s = cla.searchPartial(searchFor);
                ArrayList<Student> temp = new ArrayList<>();
                temp.add(s);
                studentModel.setItems(temp);
                studentTable.setCustomList(temp);
                studentTable.updateTable();
            }
        };
    }

    private ActionListener sortStudentButtonEvent() {
        return e -> {
            studentTable.getList().sort(Comparator.comparing(Student::getName));
            studentTable.updateTable();
        };
    }

    private ActionListener addStudentButtonEvent() {
        return e -> {
            JFrame f = new JFrame();
            String name = JOptionPane.showInputDialog(f, "Podaj nazwe");
            String surrname = JOptionPane.showInputDialog(f, "Podaj nazwisko");
            int birthYear = Integer.parseInt(JOptionPane.showInputDialog(f, "Podaj date urodzenia"));
            Student s = Student.builder()
                    .name(name)
                    .surrname(surrname)
                    .birthYear(birthYear)
                    .build();
            studentTable.addItem(s);
        };
    }

    private ActionListener removeStudentButtonEvent() {
        return e -> {
            if(studentTable.getTable().getSelectedRow() != -1) {
                studentTable.removeItemAt(studentTable.getTable().getSelectedRow());
            }
        };
    }

    private ActionListener editStudentButtonEvent() {
        return e -> {
            if(studentTable.getTable().getSelectedRow() != -1) {
                JFrame f = new JFrame();
                String name = JOptionPane.showInputDialog(f, "Edytuj nazwe");
                String surrname = JOptionPane.showInputDialog(f, "Edytuj nazwisko");
                int birthYear = Integer.parseInt(JOptionPane.showInputDialog(f, "Edytuj date urodzenia"));
                Student s = studentTable.getItemAt(studentTable.getTable().getSelectedRow());
                s.setName(name);
                s.setSurrname(surrname);
                s.setBirthYear(birthYear);
                studentTable.updateTable();
            }
        };
    }
}
