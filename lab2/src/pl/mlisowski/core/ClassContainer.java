package pl.mlisowski.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassContainer {

    private Map<String, Class> classes;


    public boolean addClass(String gname, int max) {
        Class c = new Class(gname, new ArrayList<>(), max);
        if(classes == null) classes = new HashMap<>();
        if(classes.containsKey(gname)) return false;
        classes.put(gname, c);
        return true;
    }

    public Class getClass(String name) {
        return classes.get(name);
    }

    public void removeClass(String name) {
        classes.remove(name);
    }

    public List<String> findEmpty() {
        List<String> ret = new ArrayList<>();
        for(String s : classes.keySet()) {
            if(classes.get(s)==null) ret.add(s);
        }
        return ret;
    }

    public void summary() {
        for(Map.Entry<String, Class> entry : classes.entrySet()) {
            Class c = entry.getValue();
            System.out.println(entry.getKey() + " %" + (c.getStudents().size()*100)/c.getMaxStudents());
        }
    }
}
