package pl.mlisowski;
import pl.mlisowski.dto.CircleDTO;
import pl.mlisowski.dto.PrismDTO;
import pl.mlisowski.dto.SquareDTO;
import pl.mlisowski.dto.TriangleDTO;
import pl.mlisowski.figures.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.TrustAnchor;

public class Lab1 {

    public static void main(String[] args) {
        Lab1 l = new Lab1();
        l.startMainLoop();
    }

    private Square getSquare(BufferedReader br) {
        System.out.println("Podaj bok kwadratu");
        double a = getInput(br);
        if(Double.compare(a,-1) == 0) return null;
        SquareDTO temp = new SquareDTO(a);
        Square s = new Square(temp.getA());
        return s;
    }

    private Circle getCircle(BufferedReader br) {
        System.out.println("Podaj promien kola");
        double r = getInput(br);
        if(Double.compare(r,-1) == 0) return null ;
        CircleDTO temp = new CircleDTO(r);
        Circle c = new Circle(temp.getR());
        return c;
    }

    private Triangle getTriangle(BufferedReader br, boolean flag) {
        System.out.println("Podaj bok a trojkata");
        double a = getInput(br);
        if(Double.compare(a,-1) == 0) return null;
        double b, c;
        if(!flag) {
            System.out.println("Podaj bok b trojkata");
            b = getInput(br);
            if (Double.compare(b, -1) == 0) return null;
            System.out.println("Podaj bok c trojkata");
            c = getInput(br);
            if (Double.compare(c, -1) == 0) return null;

            if (Double.compare(c, a + b) >= 0 ||
                    Double.compare(a, c + b) >= 0 ||
                    Double.compare(b, c + a) >= 0) {
                System.out.println("Twoj trojkat nie moze byc utworzony");
                return null;
            }
        } else {
            b = c = a;
        }
        TriangleDTO temp = new TriangleDTO(a, b, c);
        Triangle t = new Triangle(temp.getA(), temp.getB(), temp.getC());
        return t;
    }

    private Prism<Figure> getPrism(BufferedReader br) {
        Prism<Figure> p;
        PrismDTO temp = null;
        Figure f = null;
        System.out.println("Wybierz jaka podstawe ma miec graniastoslup");
        System.out.println("[1] Trojkat");
        System.out.println("[2] Kwadrat");
        try {
            int cmd = Integer.parseInt(br.readLine());
            if(cmd==1) {
                f = getTriangle(br, true);
            } else if(cmd==2) {
                f = getSquare(br);
            }
            System.out.println("Podaj wysokosc graniastoslupa");
            double h = getInput(br);
            if(f==null) return null;
            if(Double.compare(h,-1) == 0) return null;
            temp = new PrismDTO(f, h);
            f = null;
        } catch (IOException e) {
            System.err.println("Blad wprowadzania danych");
        }
        Figure t = temp.getF();
        double a = 0;
        String base = "";
        if(t instanceof Triangle) {
            a = ((Triangle) t).getA();
            base = "trojkat";
        } else {
            a = ((Square) t).getA();
            base = "kwadrat";
        }
        p = new Prism<>(t, a, temp.getH(), base);
        return p;
    }

    private double getInput(BufferedReader br) {
        System.out.println("Podaj nie ujemna wartosc");
        double tmp = -1;
        while(!checkIfNonNegative(tmp)) {
            if(tmp!=-1) System.out.println("Twoje wartosci musza byc wieksze od 0!");
            try {
                tmp = Double.parseDouble(br.readLine());
            } catch(IOException e){
                System.err.println("Blad wprowadzania danych");
            } catch (NumberFormatException e) {
                return -1;
            }
        }
        return tmp;
    }

    private boolean checkIfNonNegative(double a) {
        return Double.compare(a, 0) > 0;
    }

    private void figureCreation(BufferedReader br) {
        chooseFigure();
        int cmd = -1;
        try {
            cmd = Integer.parseInt(br.readLine());
            switch (cmd) {
                case 1:
                    Triangle t = getTriangle(br, false);
                    if(t!=null) t.print();
                    break;
                case 2:
                    Square s = getSquare(br);
                    if(s!=null) s.print();
                    break;
                case 3:
                    Circle c = getCircle(br);
                    if(c!=null) c.print();
                    break;
                case 4:
                    Prism<Figure> p = getPrism(br);
                    if(p!=null) p.print();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wybacz nie zrozumialem, sprobuj ponownie");
            }
        } catch (IOException e) {
            System.err.println("Wystapil blad przy wprowadzaniu danych");
        }
    }

    private void startMainLoop() {
        int cmd = -1;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            while(cmd!=0){
                printMenu();
                cmd = Integer.parseInt(br.readLine());
                switch (cmd){
                    case 1:
                        figureCreation(br);
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Wybacz nie zrozumialem, sprobuj ponownie");
                }
            }
        } catch (IOException e) {
            System.err.println("Wystapil blad, uruchom ponownie aplikacje");
        }
    }

    private void printMenu() {
        System.out.println("Wybierz jedna z ponizszych opcji");
        System.out.println("[1] Stworzenie figury");
        System.out.println("[0] Wyjscie");
    }

    private void chooseFigure() {
        System.out.println("Wybierz jedna z ponizszych figur do stworzenia");
        System.out.println("[1] Trojkat");
        System.out.println("[2] Kwadrat");
        System.out.println("[3] Kolo");
        System.out.println("[4] Graniastoslup prawidlowy");
        System.out.println("[0] Wyjscie");
    }
}
