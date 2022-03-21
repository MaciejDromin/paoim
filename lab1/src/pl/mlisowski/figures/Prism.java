package pl.mlisowski.figures;

import pl.mlisowski.Printable;

public class Prism<T extends Figure> implements Printable {

    private T t;
    private double h;
    private String base;
    private double area;
    private double v;

    public Prism(T t, double a, double h, String base){
        this.t = t;
        this.h = h;
        this.v = t.calculateArea()*h;
        this.base = base;
        if(base.equalsIgnoreCase("trojkat")) this.area = a*h*3;
        else this.area = a*h*4;
    }

    public double countArea() {
        return this.area;
    }

    public double countV() {
        return this.v;
    }

    @Override
    public String toString(){
        return ("Jestem graniastoslup prawidlowy, w podstawie mam " + this.base + " moje pole to " + this.area + " a objetosc " + this.v);
    }

    @Override
    public void print() {
        System.out.println(this);
    }
}
