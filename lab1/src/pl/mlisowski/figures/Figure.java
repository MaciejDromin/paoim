package pl.mlisowski.figures;

import pl.mlisowski.Printable;

public abstract class Figure implements Printable {

    private double area;
    private double perimeter;

    public Figure(double area, double perimeter) {
        this.area = area;
        this.perimeter = perimeter;
    }

    public double calculateArea() {
        return this.area;
    }

    public double calculatePerimeter() {
        return this.perimeter;
    }

    @Override
    public void print() {
        System.out.println(this + " polu " + this.area + " obwodzie " + this.perimeter);
    }
}
