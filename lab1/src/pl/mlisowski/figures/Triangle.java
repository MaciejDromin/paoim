package pl.mlisowski.figures;

import static java.lang.Math.sqrt;

public class Triangle extends Figure {

    private double a;
    private double b;
    private double c;
    private double h;

    public Triangle(double a, double b, double c) {
        super(heron(a,b,c),a+b+c);
        this.a = a;
        this.b = b;
        this.c = c;
        this.h = (calculateArea()*2)/a;
    }

    private static double heron(double a, double b, double c) {
        double p = (a+b+c)/2;
        return sqrt(p*(p-a)*(p-b)*(p-c));
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    @Override
    public String toString(){
        return String.format("Jestem trojkat o bokach a %f, b %f, c %f, wysokosci h %f", this.a, this.b, this.c, this.h);
    }

}
