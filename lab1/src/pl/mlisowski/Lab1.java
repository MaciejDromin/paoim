package pl.mlisowski;
import pl.mlisowski.figures.Triangle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lab1 {

    public static void main(String[] args) {
        Lab1 l = new Lab1();
        l.startMainLoop();
    }

    private void getSquare() {

    }

    private void getCircle() {

    }

    private void getTriangle() {
        //if(c<a+b) sworz trojkat, inaczej wypierdol nie
    }

    private void startMainLoop() {
        int cmd = -1;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            while(cmd!=0){
                printMenu();
                cmd = br.read();
                switch (cmd){
                    case 1:
                        break;
                    case 2:
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
        System.out.println("[2] Wyświetlenie figury");
        System.out.println("[0] Wyjscie");
    }
}
