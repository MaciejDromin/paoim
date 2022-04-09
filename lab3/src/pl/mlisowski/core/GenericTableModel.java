package pl.mlisowski.core;

import lombok.Getter;
import lombok.Setter;

import javax.swing.table.AbstractTableModel;
import java.util.List;
@Getter
@Setter
public class GenericTableModel<T> extends AbstractTableModel {

    private String[] columnNames;
    private List<T> items;

    public GenericTableModel(List<T> items, String[] columnNames) {
        this.items = items;
        this.columnNames = columnNames;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student s = null;
        Class c = null;
        if(items.get(0) instanceof Student) s = (Student) items.get(rowIndex);
        else c = (Class) items.get(rowIndex);
        if(s!=null) switch (columnIndex) {
            case 0:
                return s.getName();
            case 1:
                return s.getSurrname();
            case 2:
                return s.getBirthYear();
        }
        if(c!=null) {
            switch (columnIndex) {
                case 0:
                    return c.getGroupName();
                case 1:
                    return c.getMaxStudents();
            }
        }
        return null;
    }
}
