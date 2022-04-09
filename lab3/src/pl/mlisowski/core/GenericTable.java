package pl.mlisowski.core;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

@Getter
@Setter
public class GenericTable<T extends AbstractTableModel, S extends List<E>, E> {
    private T tableModel;
    private S customList;
    private JTable table;

    public GenericTable(T tableModel, S customList) {
        this.tableModel = tableModel;
        this.customList = customList;
        this.table = new JTable(this.tableModel);
        this.table.setAutoCreateRowSorter(true);
    }

    public void updateTable() {
        tableModel.fireTableDataChanged();
    }

    public void addItem(E item) {
        customList.add(item);
        updateTable();
    }

    public void removeItemAt(int index) {
        customList.remove(index);
        updateTable();
    }

    public E getItemAt(int index) {
        return customList.get(index);
    }

    public S getList() {
        return this.customList;
    }

    public JTable getTable() {
        return this.table;
    }

}
