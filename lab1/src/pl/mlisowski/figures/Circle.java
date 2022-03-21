package pl.mlisowski.figures;

public class Circle extends Figure{

    private static final double PI = 3.14;
    private double r;

    public Circle(double r) {
        super(PI*r*r, 2*PI*r);
        this.r = r;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    @Override
    public String toString(){
        return("Jestem kolo o promieniu r " + this.r);
    }

}
