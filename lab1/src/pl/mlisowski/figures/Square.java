package pl.mlisowski.figures;

public class Square extends Figure {

    private double a;

    public Square(int a) {
        super(a*a, 4*a);
        this.a = a;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    @Override
    public String toString(){
        return("Jestem kwadrat o boku a " + this.a);
    }
}
